package com.armadillo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.widget.Toast;
public class BlueConexion extends Activity{
	BluetoothAdapter adaptador;
	BluetoothDevice dispositivo;
	BluetoothSocket socket;
	InputStream ins;
	OutputStream ons;
	private static final int REQUEST_ENABLE_BT = 3;
	boolean [] estados;
	boolean statusbt=false;
    /** Called when the activity is first created. */
    public void Blueconexion() {
         estados = new boolean[6];
		for(int i=0;i<6;i++){
			estados[i] = false;						   
		}               
    }
    public String obtener(){
    	String temp = null;
    	try {
    		ins=socket.getInputStream();
    		ins.read();
    		temp =ins.toString();
    		return temp;   		
    	}
    	catch(IOException e){
    		return temp;   		
    	}
    	   }
    public void mision(int val){
    	try{
    		ons=socket.getOutputStream();
    			ons.write(val);    		
    		 		}
    	catch (IOException e){
				//Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show(); este es un comentario
		}    	
    }
    
    public void conectarBT(){
    	
    	try{							
    		adaptador = BluetoothAdapter.getDefaultAdapter();		
    			if (adaptador != null)
    			{
    				if (!adaptador.isEnabled())
    				{
    					Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    					startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    				}
    			}else{
    				Toast.makeText(this, "No existe un BT", Toast.LENGTH_LONG).show();
    			}
    			dispositivo = (BluetoothDevice) adaptador.getRemoteDevice("20:14:02:18:17:98");
    	}catch (Exception e){
    		Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    	}
    		
    	   try   
    	  {   
    	      socket = dispositivo.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));	// esta madre no se que hace	   		      
    	      Method m = dispositivo.getClass().getMethod("createInsecureRfcommSocket", new Class[] {int.class});		// dependiendo del valor otorgado produce una secuencia de numeros aleatorios
    	      
    	      socket = (BluetoothSocket) m.invoke(dispositivo, 1);		      
    	      socket.connect();  
    	        	     		    		     		     		    
    	  }   
    	  catch(Exception ex)   
    	  {   
    		  Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
    	  }   	   	
    }
}