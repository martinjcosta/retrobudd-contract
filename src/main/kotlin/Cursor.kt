import kotlinx.serialization.Serializable

@Serializable
enum class CursorState {
    Normal,
    Resize
}