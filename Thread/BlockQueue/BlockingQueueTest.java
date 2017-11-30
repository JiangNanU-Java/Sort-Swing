package com.Sort.Thread.BlockQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//根据输入directory目录查找子文件中包含keyword的字符串
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE=10;
    private static final int SEARCH_THREAD=100;
    private static final File DUMMY=new File("");
    private static BlockingQueue<File> queue=new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (Scanner in=new Scanner(System.in)){
            //获取查找根目录
            System.out.println("Enter base directory (e.g. /opt/jdk1.8.0/src):");
            String directory=in.nextLine();
            //获取查找关键词
            System.out.println("Enter keyword (e.g. volatile):");
            String keyword=in.nextLine();
            //启动根目录的枚举方法
            Runnable enumerator=()->{
              try {
                  //从根目录启动enumerate
                  enumerate(new File(directory));
                  //虚拟对象，标志队列结束，将其加入到队列最后
                  queue.put(DUMMY);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
            };
            new Thread(enumerator).start();
            //启动大量搜索线程
            for (int i=1;i<=SEARCH_THREAD;i++){
                Runnable searcher=()->{
                  try {
                      //标志位管理结束查找
                      boolean done=false;
                      while (!done){
                          //take:取出下一个队列项
                          File file=queue.take();
                          //如果为虚拟对象，那么将其放回，并结束进程
                          if (file==DUMMY){
                              queue.put(file);
                              done=true;
                          }
                          //若不是虚拟对象，那么继续查找search
                          else search(file,keyword);
                      }
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  } catch (FileNotFoundException e) {
                      e.printStackTrace();
                  }
                };
                new Thread(searcher).start();
            }
        }
    }
    //获取所有文件：生产者线程枚举在所有子目录下的所有文件并把它们放到一个阻塞队列中
    private static void enumerate(File directory) throws InterruptedException {
        File[] files=directory.listFiles();//listFiles方法，将目录包含的文件放进File[]数组中
        for (File file:files){
            if (file.isDirectory())enumerate(file);//如果子文件为目录，继续向下寻找
            else queue.put(file);//如果为文件，那么加入到阻塞队列中
        }
    }
    //查找每个文件：每个搜索线程从队列中取出一个文件，打开它，打印所有包含该关键字的行，然后取出下一个文件
    private static void search(File file, String keyword) throws FileNotFoundException {
        try (Scanner in=new Scanner(file,"UTF-8")){//从文件中读取字符串，采用UTF-8编码
            int lineNumber=0;
            while (in.hasNextLine()){//不断读取文件的内容
                lineNumber++;
                String line=in.nextLine();
                if (line.contains(keyword))//如果本次读取的字符串包含keyword
                    System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
            }
        }
    }
}
