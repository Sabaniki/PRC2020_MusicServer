package saba.prc.music

import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.DataLine
import kotlin.concurrent.thread

class MusicPlayer(private val musicName: String) {
	private val musicPath: String = System.getProperty("user.dir") + "/$musicName"
	
	private val ais = musicPath.let { AudioSystem.getAudioInputStream(File(musicPath)) }
	private val af = ais.format
	private val dataLine = DataLine.Info(Clip::class.java, af)
	private val clip = AudioSystem.getLine(dataLine) as Clip
	
	fun start(playLoop: Boolean) {
		try {
			if (clip.isOpen) return
			thread {
				with(clip) {
					open(ais)
					if (playLoop) loop(Clip.LOOP_CONTINUOUSLY)
					else start()
				}
			}
		}
		catch (e: Exception) {
			e.printStackTrace()
		}
	}
	
	fun stop() {
		with(clip) {
			stop()
			close()
			flush()
		}
	}
}