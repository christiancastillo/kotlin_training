package com.android.training.valesalmacenj16.classes

class ValeMedicamentosModel {

    lateinit var ClaveListaModel : String
    lateinit var PresentacionListaModel : String
    lateinit var DescripcionListaModel : String
    lateinit var CantidadListaModel : String
    lateinit var LoteListaModel : String
    lateinit var FechaCaducidadModel : String
    lateinit var RemisionListaModel : String
    lateinit var ProcedenciaListaModel : String


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