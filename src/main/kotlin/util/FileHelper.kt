package util

import java.io.File

fun String.standradAdderess()=this.replace("\\","/")
fun File.appendTextln(data:String)=this.appendText(data+"\n")
