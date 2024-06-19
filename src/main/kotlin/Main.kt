import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.skia.Color


@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, User!") }
    val sampleMessages = listOf(
        Message(1, "This is the 1st one."),
        Message(2, "This is the 2nd one.")
    )
    var showMessages by remember { mutableStateOf(false) }

    MaterialTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            GreetingText(message = text)
            Button(onClick = {
                text = "From a Stranger"
            }) {
                Text("Click me!")
            }
            Button(onClick = {
                showMessages = true
            }) {
                Text("Show Messages")
            }
            if (showMessages) {
                messageList(sampleMessages)
            }
        }
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

@Composable
fun GreetingText(message : String, modifier : Modifier = Modifier){
    Text(
        text = message,
        fontSize = 100.sp,
        lineHeight = 116.sp,
        modifier = modifier
    )
}

data class Message(val user_id:Int, val user_message :String)

@Composable
fun messageItem(message: Message) {
    Row{
        Spacer(modifier = Modifier.padding( start = 5.dp))
        Text(text = message.user_id.toString())
        Spacer(modifier = Modifier.padding( start = 5.dp))
        Text(text = message.user_message)
    }
}

@Composable
fun messageList(messages : List<Message>) {
    LazyColumn{
        items(messages){
            message -> messageItem(message)
        }
    }
}

data class Complaint(val name:String, val complaintText:String, val resolved: Boolean = false)

@Composable
fun ComplaintItem(complaint : Complaint){
    Row{
        Spacer(modifier = Modifier.padding(start = 5.dp))
        Text(text = complaint.name)
        Spacer(modifier = Modifier.padding(start = 5.dp))
        Text(text = complaint.complaintText)
        Spacer(modifier = Modifier.padding(start = 5.dp))
        Text(
            text = if (complaint.resolved) "Resolved" else "Unresolved",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ComplaintList(complaints : List<Complaint>){
    LazyColumn{
        items(complaints){
            complaint -> ComplaintItem(complaint = complaint)
        }
    }
}



