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
			println(it)
			println(it.length)
			when (it) {
//				"squeal\n" -> MusicPlayer("kamo.wav").start(false)
//				"bomb!\n" -> MusicPlayer("bomb.wav").start(false)
				"startB\n" -> bgm.start(true)
				"stopB\n" -> bgm.stop()
			}
		}
	}
}