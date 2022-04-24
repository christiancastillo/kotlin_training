package com.android.training.valesalmacenj16.classes

class ValeMedicamentosModel {

    var ClaveListaModel : String = ""
    var PresentacionListaModel : String = ""
    var DescripcionListaModel : String = ""
    var CantidadListaModel : String = ""
    var LoteListaModel : String = ""
    var FechaCaducidadModel : String = ""
    var RemisionListaModel : String = ""
    var ProcedenciaListaModel : String = ""


    fun setClaveLista(valor:String){ ClaveListaModel = valor }
    fun getClaveLista():String { return this.ClaveListaModel }

    fun setPresentacionLista(valor : String){ this.PresentacionListaModel = valor }
    fun getPresentacionLista():String{ return this.PresentacionListaModel }

    fun setDescripcionLista(valor: String){ this.DescripcionListaModel = valor }
    fun getDescripcionLista():String{ return this.DescripcionListaModel }

    fun setCantidadLista(valor: String){ this.CantidadListaModel = valor }
    fun getCantidadLista():String{ return this.CantidadListaModel }

    fun setLoteLista(valor:String){this.LoteListaModel = valor}
    fun getLoteLista():String{return this.LoteListaModel}

    fun setFechaCad(valor:String){this.FechaCaducidadModel = valor}
    fun getFechaCad():String{return this.FechaCaducidadModel}

    fun setRemision(valor: String){this.RemisionListaModel = valor}
    fun getRemision():String{return this.RemisionListaModel}

    fun setProcedencia(valor:String){this.ProcedenciaListaModel = valor}
    fun getProcedencia():String{return this.ProcedenciaListaModel}

}