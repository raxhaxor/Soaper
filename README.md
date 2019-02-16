# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###
Soaper Module to make Soap Api consuming ease using Ksoap Library.

### USAGE ###
  Thread {
            SoapApiHitter.setConfig(
                url = "http://brentwood.appsondemand.ca/Services/Scanner.asmx",
                nameSpace = "http://tempuri.org/"
            )
            val response = SoapApiHitter.callApi(
                methodName = "AdjustItemTicekts",
                action = "http://tempuri.org/AdjustItemTicekts"
                ,
                params = arrayListOf(
                    Param("DateType", 5),
                    Param("dtExpected", "2018-12-12"),
                    Param("TicketId", "58711")
                ),
                headers = arrayListOf(
                    Header(
                        mainHeader = "AuthenticationHeader",
                        params = arrayListOf(
                            Header.HeaderParam(param = "Username", value = "Cindy"),
                            Header.HeaderParam(param = "Password", value = "y0XjvvxEmfpHP5UkYm9YbMBRnIxZZEvm")
                        )
                    )
                )
            )
            when (response.status) {
                true -> Log.d("SoapReponse", response.result.toString())
                false -> Log.e("SoapError", response.message)
            }
        }.start()