/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package fabriqueurTicketsPackage
import fabriqueurTicketsPackage.views.CreatorView
import tornadofx.*
class MainApp : App(CreatorView::class){

}
fun main(args: Array<String>) {
    launch<MainApp>(args)
}