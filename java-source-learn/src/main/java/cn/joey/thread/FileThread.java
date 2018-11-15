package cn.joey.thread;

import java.io.*;

public class FileThread implements Runnable {

    private File file;
    private String data;

    public FileThread(File file,String data){
        this.file=file;
        this.data=data;
    }

    @Override
    public void run() {
        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void writeFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file.getPath(),"rw");
        raf.seek(raf.length());
        raf.write(data.getBytes());
        raf.close();

//        FileWriter writer=new FileWriter(file);
//        writer.write(data);
//        writer.flush();
//        writer.close();
    }
}
