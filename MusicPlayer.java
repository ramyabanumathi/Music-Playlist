import java.util.*;

// Node class
class Song {
    String title;
    Song next, prev;

    Song(String title) {
        this.title = title;
    }
}

// Playlist class
class Playlist {
    Song head = null, tail = null, current = null;
    boolean repeat = false;
    Random rand = new Random();

    // Add song
    void addSong(String title) {
        Song newSong = new Song(title);

        if (head == null) {
            head = tail = newSong;
        } else {
            tail.next = newSong;
            newSong.prev = tail;
            tail = newSong;
        }
        System.out.println("Song added!");
    }

    // Play
    void play() {
        if (head == null) {
            System.out.println("Playlist empty!");
            return;
        }
        current = head;
        System.out.println("Playing: " + current.title);
    }

    // Next
    void next() {
        if (current == null) {
            System.out.println("No song playing!");
            return;
        }

        if (repeat) {
            System.out.println("Repeat ON: Playing " + current.title);
            return;
        }

        if (current.next != null) {
            current = current.next;
            System.out.println("Playing: " + current.title);
        } else {
            System.out.println("End of playlist");
        }
    }

    // Previous
    void prev() {
        if (current == null) {
            System.out.println("No song playing!");
            return;
        }

        if (current.prev != null) {
            current = current.prev;
            System.out.println("Playing: " + current.title);
        } else {
            System.out.println("No previous song");
        }
    }

    // Shuffle
    void shuffle() {
        if (head == null) {
            System.out.println("Playlist empty!");
            return;
        }

        int count = 0;
        Song temp = head;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int index = rand.nextInt(count);

        temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        current = temp;
        System.out.println("Shuffle Playing: " + current.title);
    }

    // Toggle repeat
    void toggleRepeat() {
        repeat = !repeat;
        System.out.println("Repeat is now " + (repeat ? "ON" : "OFF"));
    }

    // Delete
    void deleteSong(String title) {
        Song temp = head;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {

                if (temp == head) {
                    head = temp.next;
                    if (head != null) head.prev = null;
                }

                if (temp == tail) {
                    tail = temp.prev;
                    if (tail != null) tail.next = null;
                }

                if (temp.prev != null) temp.prev.next = temp.next;
                if (temp.next != null) temp.next.prev = temp.prev;

                System.out.println("Song deleted!");
                return;
            }
            temp = temp.next;
        }

        System.out.println("Song not found!");
    }

    // Search
    void searchSong(String title) {
        Song temp = head;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                System.out.println("Song found: " + temp.title);
                return;
            }
            temp = temp.next;
        }

        System.out.println("Song not found!");
    }

    // Display
    void display() {
        if (head == null) {
            System.out.println("Playlist empty!");
            return;
        }

        Song temp = head;
        while (temp != null) {
            System.out.print(temp.title + " <-> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }
}

// Main class
public class MusicPlayer {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Playlist p = new Playlist();
        int choice;

        do {
            System.out.println("\n🎵 MUSIC PLAYER MENU 🎵");
            System.out.println("1. Add Song");
            System.out.println("2. Play");
            System.out.println("3. Next");
            System.out.println("4. Previous");
            System.out.println("5. Delete Song");
            System.out.println("6. Display Playlist");
            System.out.println("7. Search Song");
            System.out.println("8. Shuffle");
            System.out.println("9. Toggle Repeat");
            System.out.println("10. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter song name: ");
                    p.addSong(sc.nextLine());
                    break;

                case 2:
                    p.play();
                    break;

                case 3:
                    p.next();
                    break;

                case 4:
                    p.prev();
                    break;

                case 5:
                    System.out.print("Enter song to delete: ");
                    p.deleteSong(sc.nextLine());
                    break;

                case 6:
                    p.display();
                    break;

                case 7:
                    System.out.print("Enter song to search: ");
                    p.searchSong(sc.nextLine());
                    break;

                case 8:
                    p.shuffle();
                    break;

                case 9:
                    p.toggleRepeat();
                    break;

                case 10:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 10);

        sc.close();
    }
}