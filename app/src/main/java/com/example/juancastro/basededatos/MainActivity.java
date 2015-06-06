package com.example.juancastro.basededatos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // Declaramos los TextView
    EditText et_id, et_nombre_comun, et_nombre_cient, et_habitat, et_caracteristicas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = (EditText) findViewById(R.id.et_id);
        et_nombre_comun = (EditText) findViewById(R.id.et_nombre_comun);
        et_nombre_cient = (EditText) findViewById(R.id.et_nombre_cient);
        et_habitat = (EditText) findViewById(R.id.et_habitat);
        et_caracteristicas = (EditText) findViewById(R.id.et_caracteristicas);

    }

    //Código para el bóton guardar
    public void guardar (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "animales", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String id= et_id.getText().toString();
        String nombre_comun = et_nombre_comun.getText().toString();
        String nombre_cient = et_nombre_cient.getText().toString();
        String habitat = et_habitat.getText().toString();
        String caracteristicas = et_caracteristicas.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("id", id);
        registro.put("nombre_comun", nombre_comun);
        registro.put("nombre_cient", nombre_cient);
        registro.put("habitat", habitat);
        registro.put("caracteristicas", caracteristicas);


        bd.insert("animales", null, registro);
        bd.close();

        et_id.setText("");
        et_nombre_comun.setText("");
        et_nombre_cient.setText("");
        et_habitat.setText("");
        et_caracteristicas.setText("");



        Toast.makeText(this,"Se agrego un nuevo registro",Toast.LENGTH_SHORT).show();

    }
    // Código para poder buscar los registros guardados
    public void buscar (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "animales", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        // Se usa el Id para buscar algun registro
        String id = et_id.getText().toString();
        Cursor fila = bd.rawQuery("select nombre_comun, nombre_cient, habitat, caracteristicas from animales where id=" + id, null);
        if (fila.moveToFirst()) {
            et_nombre_comun.setText(fila.getString(0));
            et_nombre_cient.setText(fila.getString(1));
            et_habitat.setText(fila.getString(2));
            et_caracteristicas.setText(fila.getString(3));

        } else {
            Toast.makeText(this,"No existe el registro que desea buscar",Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    //Código para el bóton eliminar
    public void eliminar (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "animales", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = et_id.getText().toString();
        int cant = bd.delete("animales","id=" + id, null);
        bd.close();

        et_id.setText("");
        et_nombre_comun.setText("");
        et_nombre_cient.setText("");
        et_habitat.setText("");
        et_caracteristicas.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se ha eliminado el registro",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el registro que desea eliminar",Toast.LENGTH_SHORT).show();
        }
    }

    //Código para el bóton editar
    public void editar (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "animales", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        // Se extrae el EditText y sus valores guardados
        String id = et_id.getText().toString();
        String nombre_comun = et_nombre_comun.getText().toString();
        String nombre_cient = et_nombre_cient.getText().toString();
        String habitat = et_habitat.getText().toString();
        String caracteristicas = et_caracteristicas.getText().toString();


        // se genera un registro
        ContentValues registro = new ContentValues();


        registro.put("id", id);
        registro.put("nombre_comun", nombre_comun);
        registro.put("nombre_cient", nombre_cient);
        registro.put("habitat", habitat);
        registro.put("caracteristicas", caracteristicas);


        int cant = bd.update("animales", registro, "id=" + id, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "El registro ha sido modificado",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el registro que desea modificar",Toast.LENGTH_SHORT).show();
        }

    }
    // Código para limpiar la pantalla principal
    public void limpiar (View v){
        et_id.setText("");
        et_nombre_comun.setText("");
        et_nombre_cient.setText("");
        et_habitat.setText("");
        et_caracteristicas.setText("");

    }
    // Código para el boton de ver donde nos mostrara en forma de tarjeta los datos ya guardados
    public void ver (View v) {
        Intent intent = new Intent(this, activity_animal.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
