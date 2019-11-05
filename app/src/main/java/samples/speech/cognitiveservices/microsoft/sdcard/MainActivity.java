package samples.speech.cognitiveservices.microsoft.sdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button write, read, clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        read = (Button) findViewById(R.id.readData);
        write = (Button) findViewById(R.id.writeData);
        clear = (Button) findViewById(R.id.clear);

        e1 = (EditText) findViewById(R.id.edit);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = e1.getText().toString();
                try{
                    File f = new File("/sdcard/myfile.txt");
                    f.createNewFile();
                    FileOutputStream fout = new FileOutputStream(f);
                    fout.write(message.getBytes());
                    fout.close();
                    Toast.makeText(getBaseContext(),"Data Written in SDCARD",Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message;
                String buff = "";
                try{
                    File f = new File("/sdcard/myfile.txt");
                    FileInputStream fin = new FileInputStream(f);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));

                    while((message=br.readLine())!=null){
                        buff += message;
                    }

                    e1.setText(buff);
                    br.close();
                    fin.close();

                    Toast.makeText(getBaseContext(),"Data Read from SDCARD",Toast.LENGTH_LONG).show();


                }catch (Exception e){
                    Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
            }
        });



    }
}
