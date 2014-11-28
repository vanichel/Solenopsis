 #include <SoftwareSerial.h>
 #include<stdlib.h>
 long distancia1,distancia2;
 #define DHTPIN 2
 #define ultraiz 3
 #define salidaiz 4
 #define ultrade 5
 #define salidade 6
 SoftwareSerial BT(8,9); //8 RX, 9 TX.
 #define m1d 10
 #define m1i 11
 #define m2d 12
 #define m2i 13
 #define DHTTYPE DHT11
 DHT dht(DHTPIN, DHTTYPE);
 String SalidaSensores;
 int a;

 
 void setup(){
  Serial.begin(9600);
  BT.begin(9600);
  dht.begin();
  pinMode(m1d,OUTPUT);
  pinMode(m1i,OUTPUT);
  pinMode(m2d,OUTPUT);
  pinMode(m2i,OUTPUT);
  pinMode(salidaiz, OUTPUT);
  pinMode(salidade, OUTPUT);
  pinMode(ultraiz,  INPUT);
  pinMode(ultrade,  INPUT);
  SalidaSensores="";
  a=0;
 }

 void loop() {
   byte dato;
     if (BT.available()) {
           dato=BT.read();
           if(a==0){
           switch(dato){
              case 48: // 0
               adelantei();
               break;
              case 49: // 1
               adelanted();
               break; 
              case 50: // 2
               adelante();
               break; 
              case 51: // 3
               detener();
               break;
              case 52: // 4
               atras();
               break; 
              case 53: // 5
                  a=1;
                  dato=54;
                  break; 
             }}
             else if(dato==53 && a==1){
               a=0;
               detener();
             }}
     else{
      if(a==1){
       ultra();
        lecturaSensores();}
         }
       }

 void lecturaSensores(){
 /* SalidaSensores="";
  float h=dht.readHumidity();
  float t=dht.readTemperature();
  int sensorValue = analogRead(A0);
  float g = sensorValue * (5.0 / 1023.0);
   
 Serial.print("Humdedad: ");
  Serial.println(h);
  Serial.print("Temperatura: ");
  Serial.println(t);
  Serial.print("Porcentage de gas: ");
  Serial.println(g);*/
  
  Serial.print("Distancia 1: ");
  Serial.println(distancia1);
  
  Serial.print("Distancia 2: ");
  Serial.println(distancia2);
 }

 void ultra(){
  digitalWrite(salidaiz,LOW); 
  delayMicroseconds(5);
  digitalWrite(salidaiz, HIGH); 
  delayMicroseconds(10);
  tiempoiz=pulseIn(ultraiz, HIGH); 
  distancia1= int(0.017*tiempoiz);
  
  digitalWrite(salidade,LOW);
  delayMicroseconds(5);
  digitalWrite(salidade, HIGH);
  delayMicroseconds(10);
  tiempode=pulseIn(ultrade, HIGH);
  distancia2= int(0.017*tiempode);
  
  
  if(distancia1>=51&&distancia2>=51)
     {
       adelante();
     } 
     else if(distancia1<51&&distancia2<51)
     {
       atras();
       
     }
     else if(distancia1<=50)
     {
       adelantei();
    }
     else if(distancia2<=50)
    {
       adelanted();
    }
}


void adelante(){
digitalWrite(m1d,HIGH);
digitalWrite(m1i,LOW);
digitalWrite(m2d,HIGH);
digitalWrite(m2i,LOW);
}
void adelantei(){
digitalWrite(m1d,LOW);
digitalWrite(m1i,LOW);
digitalWrite(m2d,HIGH);
digitalWrite(m2i,LOW);
}
void adelanted(){
digitalWrite(m1d,HIGH);
digitalWrite(m1i,LOW);
digitalWrite(m2d,LOW);
digitalWrite(m2i,LOW);
}

void atras(){
digitalWrite(m1d,LOW);
digitalWrite(m1i,HIGH);
digitalWrite(m2d,LOW);
digitalWrite(m2i,HIGH);
}
void atrasi(){
digitalWrite(m1d,LOW);
digitalWrite(m1i,LOW);
digitalWrite(m2d,LOW);
digitalWrite(m2i,HIGH);
}
void atrasd(){
digitalWrite(m1d,LOW);
digitalWrite(m1i,HIGH);
digitalWrite(m2d,LOW);
digitalWrite(m2i,LOW);
}

void detener(){
digitalWrite(m1d,LOW);
digitalWrite(m1i,LOW);
digitalWrite(m2d,LOW);
digitalWrite(m2i,LOW);
}
