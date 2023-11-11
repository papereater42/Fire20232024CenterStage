package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvWebcam;

public class PositionDetector {
    /**
     * This connects to the phone's camera to capture an image of the rings.
     * <p>
     * Currently, this uses the {@link OpenCvInternalCamera} class to temporarily get
     * access to advanced features. However, when we switch to a web camera, this will have to be
     * refactored to use the webcam.
     */
    private OpenCvWebcam camera;

    private PositionDetectorPipeline pipeline;

    public PositionDetector(HardwareMap hardwareMap, Telemetry telemetry) {
        init(hardwareMap, telemetry);
    }

    /**
     * Initializes the Object and starts streaming (will be split up later)
     * <p>
     * This connects the camera to the pipeline and initializes development.
     * @param hardwareMap A HardwareMap which allows the creation of a phone instance
     */
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {




        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId",
                "id",
                hardwareMap.appContext.getPackageName()
        );
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Camera"), cameraMonitorViewId);





        pipeline = new PositionDetectorPipeline(telemetry);
        camera.setPipeline(pipeline);

        camera.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    public void startStreaming() {
        // Starts Streaming the Camera Contents to the phone
        camera.openCameraDeviceAsync(
                new OpenCvCamera.AsyncCameraOpenListener() {
                    @Override
                    public void onOpened() {
                        camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                    }

                    @Override
                    public void onError(int errorCode) {
                    }
                }
        );
    }

    public String getValue(){
        PositionDetectorPipeline.Position pos = getPosition();
        return pipeline.getValue();
    }

    public void stopStreaming() {
        camera.stopStreaming();
    }

    public PositionDetectorPipeline.Position getPosition() {
        return pipeline.getPosition();
    }
}