package hu.kotprog.mobilalkfejlkotprog;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class Notify {
    private static final String CHANNEL_ID="NotficationChannel";
    Context context;
    NotificationManager manager;
    public Notify(Context context)
    {
        this.context=context;
        this.manager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createChannel();
    }
    public void createChannel(){
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.O){
            return;
        }
        NotificationChannel channel=new NotificationChannel(CHANNEL_ID,"Jegyfoglalas",NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setDescription("Jegyfoglalás éretsítés");
        this.manager.createNotificationChannel(channel);
    }
    public void send(String uzen){
        NotificationCompat.Builder bob=new NotificationCompat.Builder(context,CHANNEL_ID).setContentTitle("Jegyfoglalas").setContentText(uzen).setSmallIcon(R.drawable.ic_bus_24);
        this.manager.notify(0,bob.build());
    }
}
