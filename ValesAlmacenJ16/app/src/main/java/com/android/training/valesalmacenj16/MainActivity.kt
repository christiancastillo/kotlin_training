package com.android.training.valesalmacenj16

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.text.TextPaint
import android.text.style.ParagraphStyle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.training.valesalmacenj16.classes.MyAdapter
import java.io.File
import java.io.FileOutputStream
import java.net.URI

private const val TAG : String = "MainActivity"

class MainActivity : AppCompatActivity(){
    //DEFINICION DE TEXTS
    var procedenciaSpin = ""
    private var TEXT_LOTE: String = ""
    private var TEXT_FOLIO : String = ""
    private var TEXT_FECHA_SAL : String = ""
    private var TEXT_DESTINO : String = ""
    private var TEXT_CANTIDAD : String = ""
    private var TEXT_CADUCIDAD : String = ""
    private var TEXT_PROCEDENCIA : String = ""
    private var STATUS_CB_CADUCA : Boolean = true
    private var STATUS_CB_SLOTE : Boolean = true
    //DEFINICION DE VARIABLES
    private lateinit var spinnerProcedencia : Spinner
    private lateinit var buttonSearch: Button
    private lateinit var editTextDateFSalida : EditText
    private lateinit var etDateCad : EditText
    private lateinit var editTextLote : EditText
    private lateinit var checkNoCaduca : CheckBox
    private lateinit var checkSinLote : CheckBox
    private lateinit var spinProc : Spinner
    private lateinit var etDescripcionMed : EditText
    private lateinit var etClaveMed : EditText
    private lateinit var etPresentacionMed : EditText
    private lateinit var etCantidad : EditText
    private var presentacionMedInstance : String? = ""
    private var claveMedInstance : String? = ""
    private var descripcionMedInstance : String? = ""
    private lateinit var rvListaMeds : RecyclerView
    private var fechaCalendario : String = ""
    private lateinit var btnVerReporte : Button

    var REQUEST_CODE = 200;
    var arrayStrings : ArrayList<String> = ArrayList<String>()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if (resultCode == RESULT_OK){
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun verificarPermision(){
        var permisoEscritura : Int = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permisoEscritura == PackageManager.PERMISSION_GRANTED){
            //metodo
            Toast.makeText(this,"Permiso de almacenamiento concedido",Toast.LENGTH_SHORT).show()
        } else {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),this.REQUEST_CODE)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState) //1
      //      val view: View = binding.getRoot() //llama al binding del activity
            setContentView(R.layout.activity_main)

            setTitle("Generador de vales PDF")//2

            //3: Definicion de variables
            claveMedInstance = intent.getStringExtra("claveInsumo")
            presentacionMedInstance = intent.getStringExtra("presentacion")
            descripcionMedInstance = intent.getStringExtra("descripcion")

            buttonSearch = findViewById(R.id.buttonSearch)
            editTextDateFSalida = findViewById(R.id.editTextDateFechaSalida)
            checkNoCaduca = findViewById(R.id.checkBoxSincad)
            checkSinLote = findViewById(R.id.checkBoxLote)
            editTextLote = findViewById(R.id.editTextLote)
            spinProc = findViewById(R.id.spinnerProcedencia)
            etDateCad = findViewById(R.id.editTextDateCad)
            etCantidad = findViewById(R.id.editTextQty)
            etPresentacionMed = findViewById(R.id.editTextPresentacion)
            etClaveMed = findViewById(R.id.editTextClave)
            etDescripcionMed = findViewById(R.id.editTextDescripcion)
            rvListaMeds = findViewById(R.id.recyclerview_medicamentos_lista)
            val buttonGuardar : Button = findViewById(R.id.buttonGuardar)
            btnVerReporte = findViewById(R.id.buttonVerReporte)
            spinnerProcedencia = findViewById(R.id.spinnerProcedencia)


            if(savedInstanceState != null){
                editTextLote.setText(savedInstanceState.getString(TEXT_LOTE))
            }else {
                TEXT_LOTE = editTextLote.getText().toString()
            }

            var mAdapter = MyAdapter(this,arrayStrings)
            var procedenciaStrings = arrayOf("Almacen Estatal", "Otra institución", "Jurisdicción", "Centro de Salud")
            val arrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,procedenciaStrings)
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinProc.setAdapter(arrayAdapter)

            etDescripcionMed.setText(descripcionMedInstance)
            etClaveMed.setText(claveMedInstance)
            etPresentacionMed.setText(presentacionMedInstance)

            spinProc.setOnItemSelectedListener(
                AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                        parent.getItemAtPosition(pos)
                    }
                    override fun onNothingSelected(parent: AdapterView<*>) {

                    }
                }
            }


            buttonGuardar.setOnClickListener {
//                val requestPermissionLauncher =
//                    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
//                        if (isGranted) {
                            //Toast.makeText(this,"Permiso aceptado",Toast.LENGTH_SHORT).show()
//                            // Permission is granted. Continue the action or workflow in your
//                            // app.
//                        } else {
//                            Toast.makeText(this,"Permiso denegado, por favor de permisos a la aplicación.",Toast.LENGTH_SHORT).show()
//                            // Explain to the user that the feature is unavailable because the
//                            // features requires a permission that the user has denied. At the
//                            // same time, respect the user's decision. Don't link to system
//                            // settings in an effort to convince the user to change their
//                            // decision.
//                        }
//                    }

                //TODO: Crear codigo para que cuando se le de clic al boton, preguntar si desea dar permiso
                when {
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
                        Log.i(TAG,"PERMISO CONCEDIDO")
                        AlertDialog.Builder(this).setTitle("INFORMACION")
                            .setMessage("El reporte fué creado, ¿Desea abrir el archivo?")
                            .setPositiveButton("SI",DialogInterface.OnClickListener{dialog, id ->
                                //
                            })
                    }
                    ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                        Log.i(TAG,"PERMISO DENEGADO")
                        // In an educational UI, explain to the user why your app requires this
//                        // permission for a specific feature to behave as expected. In this UI,
//                        // include a "cancel" or "no thanks" button that allows the user to
//                        // continue using your app without granting the permission. }
                        //Toast.makeText(this,"Es necesario permitir escritura en la app para generar el reporte.",Toast.LENGTH_SHORT).show()
                        val permisos: Array<String> = arrayOf("")
                        permisos[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE
                        AlertDialog.Builder(this).setTitle("- Alerta -")
                            .setMessage("Se necesita dar permiso para almacenar en medios externos.")
                            .setPositiveButton("ENTIENDO, DARE PERMISO",DialogInterface.OnClickListener { dialog, id ->
                                ActivityCompat.requestPermissions(this,permisos,0)
                            }).show()
                    }
                    else -> {
                        Log.i(TAG,"PERMISO DENEGADO?")
                        //verificarPermision()
//                        // You can directly ask for the permission.
//                        // The registered ActivityResultCallback gets the result of this request.
                        //requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    }
                }
                arrayStrings.add("[${arrayStrings.size+1}] ${etClaveMed.getText()}    ${etDescripcionMed.getText()}    ${etPresentacionMed.getText()}    ${etCantidad.getText()}    ${editTextLote.getText()}    ${etCantidad.getText()}    ${etDateCad.getText()}      ${procedenciaSpin}")
                rvListaMeds.setAdapter(mAdapter)
                rvListaMeds.setLayoutManager(LinearLayoutManager(this))
                mAdapter.notifyItemInserted(rvListaMeds.size)
            }

            btnVerReporte.setOnClickListener {
                generarPDF()
            }

            buttonSearch.setOnClickListener {
                Log.i(TAG, "ENTRA A SET ON CLICKLISTENER")
                val searchMedicineListActivity = Intent(this, SearchMedicineList::class.java)
                startActivity(searchMedicineListActivity) //cambia de actividad
            }

            editTextDateFSalida.setOnClickListener{
                showDatePicker()
                editTextDateFSalida.setText(fechaCalendario)
                fechaCalendario = ""
            }

            checkSinLote.setOnClickListener{
                if (checkSinLote.isChecked()){
                    editTextLote.setText("NO APLICA")
                } else {
                    editTextLote.setText("")
                }
            }

            checkNoCaduca.setOnClickListener{
                if (checkNoCaduca.isChecked()){
                    etDateCad.setText("NO CADUCA")
                } else {
                    etDateCad.setText("")
                }
            }

            etDateCad.setOnClickListener {
                showDatePicker()
                etDateCad.setText(fechaCalendario)
            }
        } catch (e: Exception){
            Log.e(TAG,"Error en mainActivity, onCreate(): " + e.message)
            e.printStackTrace()
                //Toast.makeText(this, "Error: " + e.message, Toast.LENGTH_LONG).show()
            }
        }

    //Funcion para datePicker
    private fun showDatePicker(){
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager,"datePicker")
        //ed.setText(fecha)
    }

    private fun onDateSelected(day: Int, month: Int, year: Int){
        var fecha = "${day}/${month+1}/${year}"
        fechaCalendario = fecha
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG,"OnRestoreInstanceState() llamado: ")
        editTextLote.setText(savedInstanceState.getString(TEXT_LOTE))
        Log.i(TAG, "valor TEXT_LOTE: ${TEXT_LOTE}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG,"OnSaveInstanceState(): Llamado")
        outState.putString(TEXT_LOTE,editTextLote.getText().toString())
        Log.i(TAG, "valor TEXT_LOTE: ${TEXT_LOTE}")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"OnResume(): Llamado")
        editTextLote.setText(TEXT_LOTE)
        Log.i(TAG, "valor TEXT_LOTE: ${TEXT_LOTE}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "OnRestart(): llamado")
        Log.i(TAG, "valor TEXT_LOTE: ${TEXT_LOTE}")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "OnPause(): llamado")
        TEXT_LOTE = editTextLote.getText().toString()
        Log.i(TAG, "valor TEXT_LOTE: ${TEXT_LOTE}")
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun cargarPDF(urlFile : Uri, context: Context){
        try{
            var openPDF = Intent(Intent.ACTION_VIEW)
            openPDF.setDataAndType(urlFile,"application/pdf")
            openPDF.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(openPDF)
        } catch(e: Exception){
            Log.e(TAG,"Error en metodo cargarPDF(): ${e.message}")
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun generarPDF(){
        //Enlace de interés: https://stackoverflow.com/questions/27941522/how-to-set-pdf-file-size-using-pdfdocument-on-android
        //Crea un nuevo documento
        //TODO: Enlace de interés (Ejemplos) https://www.tabnine.com/code/java/classes/com.itextpdf.text.pdf.PdfDocument | https://developer.android.com/reference/android/graphics/pdf/PdfDocument | https://www.programcreek.com/java-api-examples/?api=android.graphics.pdf.PdfDocument
        var aPdfDocument: PdfDocument = PdfDocument()
        val aPageInfo : PdfDocument.PageInfo = PdfDocument.PageInfo.Builder(612,792,1).create()

        //Empieza una pagina
        val aPage : PdfDocument.Page = aPdfDocument.startPage(aPageInfo)
        val aCanvas : Canvas = aPage.getCanvas()
        val paintTexto = Paint()
        val aPaint : Paint = Paint()
        val recyclerViewLista = findViewById<RecyclerView>(R.id.recyclerview_medicamentos_lista)

        paintTexto.setColor(Color.BLACK)
        aPaint.setColor(Color.BLACK)
        aCanvas.drawText("Vale de salida",70f,50f, paintTexto)
        aCanvas.drawText("JURISDICCION SANITARIA 16 JACALA", 70f, 70f, paintTexto)
        aCanvas.drawText("INFORMACION DE VALE: ",70f, 90f, paintTexto)
        aCanvas.drawText("CLAVE      DESCRIPCION       PRESENTACION      CANTIDAD     LOTE",70f,110f,paintTexto)

        var i:Int = 0
        var hijo : String = ""
        var child : View

        while(i <= recyclerViewLista.getChildCount()){
//            child = recyclerViewLista.getChildAt(i)
            //Log.i(TAG,"Child at ${i}: ${child}")
            hijo = hijo + recyclerViewLista.adapter!!.getItemId(i) + "\n"
            i++
        }
        aCanvas.drawText(hijo,70f, 125f,paintTexto)

        //canvasRect.drawRect(10f,10f,25f,25f,aPaint)
        //Finaliza la página
        aPdfDocument.finishPage(aPage)

        //Escribe el contenido del documento
        val targetPDF : String = "/storage/emulated/0/Documents/report.pdf"
        val filePath : File = File(targetPDF)
        //val pdfUri : Uri = Uri.parse(targetPDF)
        val pdfUri : Uri = Uri.fromFile(filePath)
        //TODO: Revisar FileProvider para cargar PDF en Intent https://developer.android.com/reference/kotlin/androidx/core/content/FileProvider
        //val pdfUri = FileProvider.getUriForFile(this,"com.android.training.valesalmacenj16",filePath)

        try{
            aPdfDocument.writeTo(FileOutputStream(filePath))
            Toast.makeText(this,"PDF generado en ruta ${targetPDF}",Toast.LENGTH_SHORT).show()
            //cargarPDF(pdfUri,this)
        } catch(e: Exception){
            Log.e(TAG,"Error en método generarPDF(): ${e.message}")
            e.printStackTrace()
        } finally {
            aPdfDocument.close()
        }
    }
}