package saba.prc.music

fun main() {
	println("current dir is  " + System.getProperty("user.dir"))
	val portFromRasp = 3333
	val udpFromRasp = UdpConnection(16, "f")
	var received: String? = null
	val bgm = MusicPlayer("VsGymLeader_BW.wav")
	println("Music server is running!")
	while (received != udpFromRasp.finishing) {
		received = udpFromRasp.receive(portFromRasp).text
		received.let {
			print(it)
			print(it.length)
//			if(it.contains("wh")){
//				println("キテマス")
//				MusicPlayer("whistle.mp3").start(false)
//			}
			when (it) {
//				"squeal\n" -> MusicPlayer("kamo.wav").start(false)
				"wh\n" -> MusicPlayer("whistle.wav").start(false)
				"st\n" -> MusicPlayer("PRC_melo.wav").start(false)
//				"startB\n" -> bgm.start(true)
//				"stopB\n" -> bgm.stop()
			}
		}
	}
}