import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

@Serializable
data class User(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name: String
)

object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = String.serializer().descriptor

    override fun deserialize(decoder: Decoder): UUID = UUID.fromString(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: UUID) = encoder.encodeString(value.toString())
}
