package akash.secured.root.soaper.model

import java.util.*

/**
 * Created by Akash Saggu(R4X) on 2/13/19 at 4:53 PM
 * akashsaggu@protonmail.com
 * @Version 1 2/13/19
 * @Updated on 2/13/19
 */
data class Header(val mainHeader: String, val params: ArrayList<HeaderParam>) {

    data class HeaderParam(val param: String, val value: String)
}