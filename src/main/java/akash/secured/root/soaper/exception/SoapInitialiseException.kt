package akash.secured.root.soaper.exception

/**
 * Created by Akash Saggu(R4X) on 2/13/19 at 5:19 PM
 * akashsaggu@protonmail.com
 * @Version 1 2/13/19
 * @Updated on 2/13/19
 */
class SoapInitialiseException : IllegalStateException() {

    override fun getLocalizedMessage(): String {
        return "Initialise Url and Namespace using setConfig(url, namespace) first."
    }
}