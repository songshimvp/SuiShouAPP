package songshi.suishou.tool;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import android.media.MediaRecorder;

public class AudioManage {

	private MediaRecorder mMediaRecorder;
	private String mDir;
	private String mCurrentFilePath;

	private static AudioManage mInstance;

	private boolean isPrepared;

	private AudioManage(String dir) {
		mDir = dir;
	}

	public interface AudioStateListenter {
		void wellPrepared();
	}

	public AudioStateListenter mListenter;

	public void setOnAudioStateListenter(AudioStateListenter audioStateListenter) {
		mListenter = audioStateListenter;
	}

	public static AudioManage getInstance(String dir) {
		if (mInstance == null) {
			synchronized (AudioManage.class) {
				if (mInstance == null) {
					mInstance = new AudioManage(dir);
				}
			}
		}

		return mInstance;
	}

	public void prepareAudio() {

		try {
			isPrepared = false;

			File dir = new File(mDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			String fileName = GenerateFileName();
			File file = new File(dir, fileName);

			mCurrentFilePath = file.getAbsolutePath();

			mMediaRecorder = new MediaRecorder();
			mMediaRecorder.setOutputFile(file.getAbsolutePath());// 设置输出文件
			mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置MediaRecorder的音频源为麦克风
			mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);// 设置音频的格式
			mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);// 设置音频的编码为AMR_NB

			mMediaRecorder.prepare();

			mMediaRecorder.start();
			isPrepared = true; // 准备结束

			if (mListenter != null) {
				mListenter.wellPrepared();
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 随机生成文件名称
	private String GenerateFileName() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString() + ".amr"; // 音频文件格式
	}

	public int getVoiceLevel(int maxLevel) {
		if (isPrepared) {
			try {
				return maxLevel * mMediaRecorder.getMaxAmplitude() / 32768 + 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
		return 1;
	}

	public void release() {
		mMediaRecorder.stop();
		mMediaRecorder.release();
		mMediaRecorder = null;
	}

	public void cancel() {

		release();

		if (mCurrentFilePath != null) {
			File file = new File(mCurrentFilePath);
			file.delete();
			mCurrentFilePath = null;
		}
	}

	public String getCurrentFilePath() {
		// TODO Auto-generated method stub
		return mCurrentFilePath;
	}
}
