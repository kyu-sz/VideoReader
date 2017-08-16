package per.szykk1993.video_reader;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VideoReader {

    public static void main(String[] args)
    {
        final String video1 = "CAM01-20140226110113-20140226110701.mp4";
        final String video2 = "001_scene1_nm_L_015_1.mp4";
        final String video = video1;

        FFmpegFrameGrabber grabber;
        try {
            grabber = new FFmpegFrameGrabber(new FileInputStream(video));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            grabber.start();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
            return;
        }
        while (true)
        {
            try {
                Frame frame = grabber.grabImage();
                if (frame == null) {
                    break;
                }
                final opencv_core.Mat cvFrame = new OpenCVFrameConverter.ToMat().convert(frame);
                org.bytedeco.javacpp.opencv_highgui.imshow(video, cvFrame);
                org.bytedeco.javacpp.opencv_highgui.waitKey(1);
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("Reading finished!");
    }
}
