import kotlinx.serialization.Serializable

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
    val beingEditedByUserId: String? = null,
    val beingDraggedByUserId: String? = null,
    val beingResizedByUserId: String? = null
)

@Serializable
data class Color(
    val red: Int,
    val green: Int,
    val blue: Int
)

typealias ModifyNoteState = @Serializable (NoteState) -> @Serializable NoteState
typealias ModifyBoardState = @Serializable (BoardState) -> @Serializable BoardState
typealias SuspendModifyBoardState = @Serializable suspend (BoardState) -> @Serializable BoardState
