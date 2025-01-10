import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Kelas DatabaseConnection bertugas untuk menangani koneksi ke database MySQL.
 */
public class DatabaseConnection {
    // URL koneksi ke database (sesuaikan nama database, port, dan host Anda)
    private static final String URL = "jdbc:mysql://localhost:3306/ServiceGadget";
    
    // Username dan password MySQL (sesuaikan dengan pengaturan MySQL Anda)
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Metode ini digunakan untuk membuat dan mengembalikan koneksi ke database.
     *
     * @return Connection jika koneksi berhasil, null jika koneksi gagal.
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            // Memuat driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Membuka koneksi ke database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi ke database berhasil!");
        } catch (ClassNotFoundException e) {
            // Jika driver JDBC tidak ditemukan
            System.err.println("Driver JDBC tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            // Jika koneksi ke database gagal
            System.err.println("Koneksi ke database gagal: " + e.getMessage());
        }

        return conn; // Mengembalikan objek Connection (null jika gagal)
    }

    /**
     * Metode ini digunakan untuk menutup koneksi ke database.
     *
     * @param conn Connection yang akan ditutup.
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Koneksi ke database ditutup!");
            } catch (SQLException e) {
                System.err.println("Gagal menutup koneksi: " + e.getMessage());
            }
        }
    }

    /**
     * Metode main untuk menguji koneksi ke database.
     *
     * @param args Argumen baris perintah (tidak digunakan).
     */
    public static void main(String[] args) {
        // Uji koneksi database
        Connection conn = connect();

        // Pastikan koneksi berhasil sebelum melanjutkan
        if (conn != null) {
            System.out.println("Koneksi ke database berhasil diuji!");
        } else {
            System.out.println("Koneksi ke database gagal diuji!");
        }

        // Tutup koneksi setelah selesai
        closeConnection(conn);
    }
}
