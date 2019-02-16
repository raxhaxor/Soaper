package akash.secured.root.soaper.model

import org.ksoap2.serialization.SoapObject

/**
 * Created by Akash Saggu(R4X) on 2/13/19 at 5:23 PM
 * akashsaggu@protonmail.com
 * @Version 1 2/13/19
 * @Updated on 2/13/19
 */
data class SoapResult(val status: Boolean, val message: String, val result: SoapObject?)