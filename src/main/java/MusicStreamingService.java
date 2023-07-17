import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Song {
    private String name;
    private String artist;
    private String album;
    private int duration;

    public Song(String name, String artist, String album, int duration) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }
}

class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public void play() {
        System.out.println("Playing playlist: " + name);
        for (Song song : songs) {
            System.out.println("Now playing: " + song.getName() + " by " + song.getArtist()+" from Album "+song.getAlbum()+". Its "+song.getDuration()+" seconds long.");
        }
    }
}

public class MusicStreamingService {
    public static void main(String[] args) {
            List<Playlist> playlist = new ArrayList<>();
        List<Song> songs = new ArrayList<>();
        //User Input
        String songName,artistName,albumName,playlistName;
        int songDuration;
        Scanner sc=new Scanner(System.in);

        // Create some songs
        Song song1 = new Song("Song 1", "Artist 1", "Album 1", 180);
        Song song2 = new Song("Song 2", "Artist 2", "Album 2", 240);
        Song song3 = new Song("Song 3", "Artist 3", "Album 3", 300);


//        System.out.println("1.Create a Playlist and add the songs to it");
//        System.out.println("2:Enter info. about the song");
//        System.out.println("3:Remove a song");
//        System.out.println("4:Play a playlist");
//        System.out.println("3:Exit the Music Streaming Service");
//        int choice = 1;
//        while(choice>=1 && choice <=3)
//        {
//            System.out.println("Choice: ");
//            choice = sc.nextInt();
//            if(choice == 1)
//            {
//                System.out.println("Enter the playlist name: ");
//                playlistName = sc.next();
//                playlist.add(new Playlist(playlistName));
//                System.out.println("Enter the number of songs to be added to the playlist: ");
//                int limit = sc.nextInt();
//                if(limit < songs.size()){
//                for(int i=0;i<limit;i++) {
//                    System.out.println("Enter the song name to be added:");
//                    songName = sc.next();
//                    if(Objects.equals(songs.get(i).getName(), songName))
//                    {
//                        playlist.get(i).addSong(songs.get(i));
//                    }
//                    else{
//                        System.out.println("No such song found");
//                    }
//                }
//
//            }
//            else if(choice == 2) {
//                System.out.println("Enter the name of the song: ");
//                songName=sc.next();
//                System.out.println("Enter the artist name: ");
//                artistName = sc.next();
//                System.out.println("Enter album name: ");
//                albumName = sc.next();
//                System.out.println("Enter duration of the song:");
//                songDuration = sc.nextInt();
//                songs.add(new Song(songName,artistName,albumName,songDuration));
//            }
//            else if(choice == 3){
//                System.out.println("Enter the playlist name: ");
//                playlistName = sc.next();
//                System.out.println("Enter the name of the song: ");
//                songName=sc.next();
//                for (Playlist value : playlist) {
//                    if (Objects.equals(value.getName(), playlistName)) {
//                        for (Song song : songs) {
//                            if (Objects.equals(song.getName(), songName)) {
//                                value.removeSong(song);
//                            } else {
//                                System.out.println("No such song found");
//                            }
//                        }
//                    }
//                }
//            }
//            else if(choice == 4)
//            {
//                System.out.println("Enter the playlist name: ");
//                playlistName = sc.next();
//                for (Playlist value : playlist)
//                {
//                    if(Objects.equals(value.getName(), playlistName))
//                    {
//                        value.play();
//                    }
//                }
//            }
//            else {
//                System.out.println("Wrong choice");
//            }
//        }
            // Create playlists and add songs to them
        Playlist playlist1 = new Playlist("Playlist1");
        playlist1.addSong(song1);
        playlist1.addSong(song2);

        Playlist playlist2 = new Playlist("Playlist 2");
        playlist2.addSong(song2);
        playlist2.addSong(song3);

        // Play the playlists
        playlist1.play();
        playlist2.play();

        // Remove a song from a playlist
        playlist1.removeSong(song2);
        playlist1.play();
    }
}