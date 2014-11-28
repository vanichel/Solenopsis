package com.armadillo;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements SensorEventListener{
	TextView texto;
	TextView sensor;
	StringBuilder builder = new StringBuilder();
	ImageView referencia;
    BlueConexion blue = new BlueConexion();
    ToggleButton automatico;
    int bandera;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bandera = 0;
        blue.conectarBT();
        automatico = (ToggleButton) findViewById(R.id.auto);
        texto = (TextView)findViewById(R.id.Texto);
        sensor = (TextView) findViewById(R.id.sensores);
        referencia = (ImageView) findViewById(R.id.ImageRefe);
        
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(android.hardware.Sensor.TYPE_ACCELEROMETER).size()==0){
        	texto.setText("no hay acelerometro");
        }
        else {
        	android.hardware.Sensor accelerometer = manager.getSensorList(android.hardware.Sensor.TYPE_ACCELEROMETER).get(0);
        	if(!manager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_GAME)){
        		texto.setText("no se ha podido registrar el sensor listener");
        	}
        }
        
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float valor = event.values[0];
		float valor2 = event.values[1];
		float valor3 = event.values[2];
		
		
				 if(valor>3){
					texto.setText("izquierda");
					referencia.setImageResource(R.drawable.flecha_izquierdas);
					blue.mision(49);
					
					
				}
			 
				if (valor > -3 && valor <3){
					 if (valor2<7){
					valor =1;
					blue.mision(50);
					referencia.setImageResource(R.drawable.flecha_adelante);
					
					}
					 }
				if (valor2>7){
					blue.mision(51);
					
				}
				
				 if ( valor<-3 ){
					 {
					texto.setText("derecho");
					blue.mision(48);
					referencia.setImageResource(R.drawable.flecha_derechas);
				}}
				 if (valor2< 8.7 && valor3 <-3){
					 blue.mision(52); 
				 }
			
				 
			
		 
}
public void autom(View v){
   
    	blue.mision(53);
    	bandera = 0;
   
}

}
	
