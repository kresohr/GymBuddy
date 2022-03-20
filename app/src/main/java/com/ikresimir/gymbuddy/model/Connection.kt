import android.os.Build
import androidx.annotation.RequiresApi
import org.jetbrains.exposed.sql.Database

    //Connection through emulator
    var connection = Database.connect("jdbc:postgresql://10.0.2.2/postgres", driver = "org.postgresql.Driver", user = "postgres", password = "admin")

    // Connection through local PC for testing purposes (Console logging, etc.)
    //var connection = Database.connect("jdbc:postgresql://localhost/postgres", driver = "org.postgresql.Driver", user = "postgres", password = "admin")

    //Connection through physical device (Make sure the device and the app are on the same network)
    //var connection = Database.connect("jdbc:postgresql://192.168.0.101/postgres", driver = "org.postgresql.Driver", user = "postgres", password = "admin")

object Databasez{
    init{
        connection.useNestedTransactions
    }
}