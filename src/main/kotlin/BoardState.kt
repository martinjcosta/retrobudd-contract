import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.awt.Color

@Serializable
data class BoardState(
    val notes: List<NoteState> = mutableListOf(),
    val lastNoteAdded: NoteState? = null
)

@Serializable
data class NoteState(
    val id: String,
    val title: String = "Title",
    val content: String = "Hey, I'm a note!",
    @Serializable(with = ColorSerializer::class)
    val color: Color,
    val author: User,
    val width: Float,
    val height: Float,
    val offsetX: Float,
    val offsetY: Float,
    val zIndex: Float = 0f,
    val cursor: CursorState = CursorState.Normal,
    val isAStack: Boolean = false, // Represents whether this note is an "infinitely" stacked note.
    val isEditable: Boolean = false,
    val isBeingEdited: Boolean = false
)

object ColorSerializer : KSerializer<Color> {
    override val descriptor = String.serializer().descriptor

    override fun deserialize(decoder: Decoder): Color = Color.decode(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Color) = encoder.encodeString(value.toString())
}

typealias ModifyNoteState = (NoteState) -> NoteState
typealias ModifyBoardState = (BoardState) -> BoardState
typealias SuspendModifyBoardState = suspend (BoardState) -> BoardState
