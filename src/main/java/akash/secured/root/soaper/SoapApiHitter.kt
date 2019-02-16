package akash.secured.root.soaper

import akash.secured.root.soaper.exception.SoapInitialiseException
import akash.secured.root.soaper.model.Header
import akash.secured.root.soaper.model.Param
import akash.secured.root.soaper.model.SoapResult
import android.support.annotation.WorkerThread
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import org.kxml2.kdom.Element
import org.kxml2.kdom.Node
import java.util.*

/**
 * Created by Akash Saggu(R4X) on 2/13/19 at 3:58 PM
 * akashsaggu@protonmail.com
 * @Version 1 2/13/19
 * @Updated on 2/13/19
 */

object SoapApiHitter {
    var SOAP_NAMESPACE = ""
    var SOAP_URL = ""


    fun setConfig(url: String, nameSpace: String) {
        SOAP_NAMESPACE = nameSpace
        SOAP_URL = url
    }


    @WorkerThread
    fun callApi(
        methodName: String,
        action: String,
        params: ArrayList<Param>,
        headers: ArrayList<Header> = arrayListOf()
    ): SoapResult {
        if (SOAP_NAMESPACE.isBlank() || SOAP_URL.isBlank()) {
            throw SoapInitialiseException()
        }
        val request = SoapObject(SOAP_NAMESPACE, methodName)
        val requestHeaders = arrayListOf<Element>()
        headers.forEach {
            val mainHeader = Element().createElement(SOAP_NAMESPACE, it.mainHeader)
            it.params.forEach { headerA ->
                val header = Element().createElement(SOAP_NAMESPACE, headerA.param)
                header.addChild(Node.TEXT, headerA.value)
                mainHeader.addChild(Node.ELEMENT, header)
            }
            requestHeaders.add(mainHeader)
        }

        params.forEach {
            val property = PropertyInfo()
            property.setName(it.name)

            when (it.value) {
                is String -> {
                    addValueAndTypeToProperty<String>(property, it)
                }
                is Int -> {
                    addValueAndTypeToProperty<Int>(property, it)
                }
                is Long -> {
                    addValueAndTypeToProperty<Long>(property, it)
                }
                is Float -> {
                    addValueAndTypeToProperty<Float>(property, it)
                }
                Double::class -> {
                    addValueAndTypeToProperty<Double>(property, it)
                }
                Boolean::class -> {
                    addValueAndTypeToProperty<Boolean>(property, it)
                }
                Byte::class -> {
                    addValueAndTypeToProperty<Byte>(property, it)
                }
                Date::class -> {
                    addValueAndTypeToProperty<Date>(property, it)
                }
            }

            request.addProperty(property)
        }


        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        val requestHeaderArray = arrayOfNulls<Element>(requestHeaders.size)
        requestHeaders.toArray(requestHeaderArray)
        envelope.headerOut = requestHeaderArray
        envelope.dotNet = true
        envelope.setOutputSoapObject(request)

        val androidHttpTransport = HttpTransportSE(SOAP_URL)
        return try {
            androidHttpTransport.call(action, envelope)
            val response = envelope.bodyIn as SoapObject
            SoapResult(true, "Success", response)
        } catch (e: Exception) {
            SoapResult(true, "Success", null)
        }

    }

    private inline fun <reified T> addValueAndTypeToProperty(
        property: PropertyInfo,
        it: Param
    ) {
        property.setType(T::class.java)
        property.value = it.value as T
    }


}