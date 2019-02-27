package com.simarro.practicas.goliath_national_bank.vista;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.simarro.practicas.goliath_national_bank.bd.MiBD;
import com.simarro.practicas.goliath_national_bank.dao.CuentaDAO;
import com.simarro.practicas.goliath_national_bank.pojo.Cliente;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;
import com.simarro.practicas.goliath_national_bank.pojo.Movimiento;
import com.simarro.practicas.goliath_bank.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Transferencias extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
    Cliente cliente;
    ArrayList<String> listaCuentasString =new ArrayList<>();
    ArrayList<Cuenta> listaCuentas =new ArrayList<>();
    String [] listaMonedas = new String[]{"€","$","£","\u20BF"};
    ArrayAdapter<String>adaptadorString=null;
    ArrayAdapter<String>adaptadorSpinner=null;
    ArrayAdapter<String>adaptadorDivisas=null;
    GridView gv=null;
    RadioButton radio1=null;
    RadioButton radio2=null;
    RadioGroup grupoRadio=null;
    CheckBox checky=null;
    Button benviar=null;
    Button bcancelar=null;
    View itemLista=null;
    String seleccion_radio=null;
    EditText importe=null;
    int tipoMov=0;

    Spinner sp=null;
    Spinner spinerdivisas=null;
    Spinner spinnerDentro=null;
    TextView textoCuenta=null;
    Cuenta objCuenta=null;
    Cuenta objCuentaRadio=null;
    String strCuentaDestino=null;

    CuentaDAO cuentaDAO=new CuentaDAO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias);
        gv=findViewById(R.id.cuentasGrid);
        Intent intento=getIntent();
        this.cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        Cuenta c = new Cuenta();

        this.listaCuentas =this.cuentaDAO.getCuentas(cliente);
        this.listaCuentasString =cuentasToString(listaCuentas);

        adaptadorString=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listaCuentasString);
        adaptadorSpinner=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,this.listaCuentasString);
        this.adaptadorDivisas=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,this.listaMonedas);
        gv.setAdapter(adaptadorString);
        gv.setOnItemClickListener(this);

        //Referencias...
        radio1=findViewById(R.id.radioCpropia);
        radio2=findViewById(R.id.radioCajena);
        sp=findViewById(R.id.spinnerDentro);
        benviar=findViewById(R.id.benviar);
        bcancelar=findViewById(R.id.bcancelar);
        spinerdivisas=findViewById(R.id.spinerDivisas);
        textoCuenta=findViewById(R.id.textCuenta);
        importe =findViewById(R.id.editText4);
        grupoRadio=findViewById(R.id.radioGroup);
        checky=findViewById(R.id.checkBox);


        radio1.setOnClickListener(this);
        radio2.setOnClickListener(this);
        benviar.setOnClickListener(this);
        bcancelar.setOnClickListener(this);
        this.textoCuenta.setOnClickListener(this);
        adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adaptadorSpinner);
        this.sp.setOnItemSelectedListener(this);
        this.spinerdivisas.setAdapter(this.adaptadorDivisas);
        //this.spinnerDentro.setAdapter(adaptadorSpinner);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Seleccionaste "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
        if(this.itemLista!=null){
            this.itemLista.setBackgroundColor(Color.WHITE);
        }
        this.itemLista=view;
        this.objCuenta=this.listaCuentas.get(position);
        view.setBackgroundColor(R.color.colorAccent);
    }

    @Override
    public void onBackPressed() {
        Intent menu = new Intent(this, MenuActivity.class);
        menu.putExtra("Cliente", this.cliente);
        startActivity(menu);
        finish();
    }

    @Override
    public void onClick(View v) {

        if(v.getTag().toString().equalsIgnoreCase("radio1")){
            Toast.makeText(this,"le diste al 1",Toast.LENGTH_LONG).show();
            this.seleccion_radio="RADIO 1 - Seleccionaste cuenta propia y esta es : ";
            ViewGroup.LayoutParams params = this.sp.getLayoutParams();
            params.width= ActionBar.LayoutParams.MATCH_PARENT;
            params.height= ActionBar.LayoutParams.MATCH_PARENT;
            this.sp.setLayoutParams(params);
            this.sp.setVisibility(View.VISIBLE);

            ViewGroup.LayoutParams params2 = this.textoCuenta.getLayoutParams();
            params2.width= 1;
            params2.height= 1;
            this.textoCuenta.setLayoutParams(params2);
            this.textoCuenta.setVisibility(View.INVISIBLE);

            this.objCuentaRadio=this.listaCuentas.get(this.sp.getSelectedItemPosition());
            System.out.println("SELECCIONADA CUENA RADIO "+this.objCuentaRadio);
        }



        if(v.getTag().toString().equalsIgnoreCase("radio2")){
            Toast.makeText(this,"le diste al 2",Toast.LENGTH_LONG).show();
            this.seleccion_radio="RADIO 2 - Seleccionaste cuenta ajena y esta es : ";
            textoCuenta=findViewById(R.id.textCuenta);
            this.textoCuenta.setOnClickListener(this);
            ViewGroup.LayoutParams params = this.textoCuenta.getLayoutParams();
            params.width= ActionBar.LayoutParams.MATCH_PARENT;
            params.height= ActionBar.LayoutParams.MATCH_PARENT;
            this.textoCuenta.setLayoutParams(params);
            this.textoCuenta.setVisibility(View.VISIBLE);


            ViewGroup.LayoutParams params2 = this.sp.getLayoutParams();
            params2.width= 1;
            params2.height= 1;
            this.sp.setLayoutParams(params2);
            this.sp.setVisibility(View.INVISIBLE);

        }

        if(v.getTag().toString().equalsIgnoreCase("enviarBtn")) {
            if(this.comprobarFormulario()) {
                Movimiento mov=null;
                Random ran = new Random();
                String desc="TRANSFERENCIA A " + this.returnSelectedItem();
                this.strCuentaDestino=this.textoCuenta.getText().toString();
                int x = 1 + ran.nextInt((1000 - 1) + 1);

                if(this.grupoRadio.getCheckedRadioButtonId()==R.id.radioCpropia){
                    mov=new Movimiento(x,this.tipoMov, Calendar.getInstance().getTime(),desc,Float.parseFloat("-"+this.importe.getText().toString()),this.objCuenta,this.objCuentaRadio);
                    System.out.println("YE" + mov);
                }else{
                    String banco=this.textoCuenta.getText().toString().substring(0,4);
                    String sucursal=this.textoCuenta.getText().toString().substring(4,8);
                    String dc=this.textoCuenta.getText().toString().substring(8,10);
                    String numCuenta=this.textoCuenta.getText().toString().substring(10);
                    System.out.println(banco + "/"+sucursal+"/"+dc+"/"+numCuenta);

                    if(MiBD.getInstance(this).existeCuenta(banco,sucursal,dc,numCuenta)){
                        Cuenta c = new Cuenta();
                        c.setBanco(banco);
                        c.setSucursal(sucursal);
                        c.setDc(dc);
                        c.setNumeroCuenta(numCuenta);
                        Cuenta cFull= (Cuenta) this.cuentaDAO.search(c);
                        mov=new Movimiento(x,this.tipoMov, Calendar.getInstance().getTime(),desc,Float.parseFloat("-"+this.importe.getText().toString()),this.objCuenta,cFull);
                        System.out.println(mov);
                    }else{
                        System.out.println("No existe la cuenta!");
                    }

                }
                int resInt=this.transferencia(mov);
                if(resInt==0){
                    Toast.makeText(this,"Transferencia realizada con exito",Toast.LENGTH_SHORT).show();
                }else if(resInt==2){
                    Toast.makeText(this,"No tiene suficiente saldo!",Toast.LENGTH_SHORT).show();
                }else if(resInt==1){
                    Toast.makeText(this,"No se puede enviar a la misma cuenta!!!",Toast.LENGTH_SHORT).show();
                }
            }

        }

        if(v.getTag().toString().equalsIgnoreCase("cancelarBtn")){
            this.itemLista.setBackgroundColor(Color.WHITE);
            this.importe.setText("");
            if(checky.isSelected()) {
                this.checky.toggle();
            }
            this.spinerdivisas.clearFocus();
            this.grupoRadio.clearCheck();
        }

    }

    public String returnSelectedItem(){
        if(this.radio2.isSelected()){
            return this.textoCuenta.getText().toString();
        }else{
            return this.sp.getSelectedItem().toString();
        }

    }
    public String returnCheckboxString(){
        if(this.checky.isSelected()){
            return "Se desea enviar justificante";
        }else{
            return "No se desea enviar justificante";
        }

    }
    public int transferencia(Movimiento movimientoTransferencia){
        float res=movimientoTransferencia.getCuentaOrigen().getSaldoActual() - movimientoTransferencia.getImporte();
        System.out.println("COMPARANDO " + movimientoTransferencia.getCuentaDestino().getNumeroCuenta() + " CON "+movimientoTransferencia.getCuentaOrigen().getNumeroCuenta() );
        if(movimientoTransferencia.getCuentaDestino().getNumeroCuenta().equalsIgnoreCase(movimientoTransferencia.getCuentaOrigen().getNumeroCuenta())){
            return 1;
        }
        if(res>0){
            if(movimientoTransferencia.getCuentaDestino().getBanco().equalsIgnoreCase(movimientoTransferencia.getCuentaOrigen().getBanco())) {
                movimientoTransferencia.getCuentaOrigen().setSaldoActual(movimientoTransferencia.getCuentaOrigen().getSaldoActual() - movimientoTransferencia.getImporte());
                movimientoTransferencia.getCuentaDestino().setSaldoActual(movimientoTransferencia.getCuentaDestino().getSaldoActual() + movimientoTransferencia.getImporte());
                MiBD.getInstance(this).actualizarSaldo(movimientoTransferencia.getCuentaOrigen());
                MiBD.getInstance(this).actualizarSaldo(movimientoTransferencia.getCuentaDestino());
                MiBD.getInstance(this).insercionMovimiento(movimientoTransferencia);
                return 0;
            }else{
                Toast.makeText(this,"Deben ser del mismo banco las cuentas...",Toast.LENGTH_SHORT).show();
            }
        }else{
            return 2;
        }

        return 0;
    }

    public boolean comprobarFormulario(){
        boolean comprobacion=true;
        String mensaje="";

        if(this.itemLista==null){
            comprobacion=false;
            mensaje=mensaje + "Debes elegir una de tus cuentas primero antes de continuar! \n";
        }
        if(this.grupoRadio.getCheckedRadioButtonId()==-1){
            comprobacion=false;
            mensaje=mensaje + "Debes elegir una de las opciones! \n";
        }
        if(this.importe.getText().toString().length()==0){
            comprobacion=false;
            mensaje=mensaje + "Elige el importe! \n";
        }
        if(comprobacion==false) {
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
        return comprobacion;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.objCuentaRadio=this.listaCuentas.get(position);
        System.out.println("SELECCIONADA CUENTA RADIO "+this.objCuentaRadio);
    }

    private ArrayList<String> cuentasToString(ArrayList<Cuenta> cuentas){
        ArrayList<String> cuentasString = new ArrayList<>();
        for(Cuenta c: cuentas){
            cuentasString.add(c.getBanco()+c.getSucursal()+c.getDc()+c.getNumeroCuenta());
        }
        return cuentasString;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
