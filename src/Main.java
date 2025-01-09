import ui.MainForm;
import utils.MusicPlayer;

public class Main {
    public static void main(String[] args) {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.playMusic("assets/music.wav");

        new MainForm(); // Menampilkan Form Utama
    }
}


