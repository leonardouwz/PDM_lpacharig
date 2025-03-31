/**
Descripción del problema:       Se desea un reproductor de música básico con botones de reproducción.
Autor:                          Leonardo Pachari
Fecha de creación:              29/03/25
Fecha última de modificación:   30/03/25
 */

package com.example.practica3_ejercicio2

import android.content.res.AssetFileDescriptor
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Declarar el MediaPlayer como variable de clase
    private var mediaPlayer: MediaPlayer? = null

    // Lista de canciones (IDs de recursos)
    private val songList = listOf(
        R.raw.cancion1,
        R.raw.cancion2
    )

    // Lista de portadas (IDs de recursos)
    private val albumCovers = listOf(
        R.drawable.portada1,
        R.drawable.portada2
    )

    // Metadatos de las canciones
    private val songMetadata = mutableListOf<Map<String, String>>()

    // Índice de la canción actual
    private var currentSongIndex = 0

    // Handler para actualizar la SeekBar
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cargar metadatos de todas las canciones
        loadAllSongMetadata()

        // Inicializar referencias a elementos de la UI
        val playButton = findViewById<Button>(R.id.playButton)
        val pauseButton = findViewById<Button>(R.id.pauseButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        val prevButton = findViewById<Button>(R.id.prevButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val songTitleTextView = findViewById<TextView>(R.id.songTitleTextView)
        val artistTextView = findViewById<TextView>(R.id.artistTextView)
        val albumCoverImageView = findViewById<ImageView>(R.id.albumCoverImageView)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val currentTimeTextView = findViewById<TextView>(R.id.currentTimeTextView)
        val totalTimeTextView = findViewById<TextView>(R.id.totalTimeTextView)

        // Configurar la canción inicial
        updateSongInfo()

        // Configurar el botón de reproducción
        playButton.setOnClickListener {
            if (mediaPlayer == null) {
                prepareMediaPlayer()
            }

            mediaPlayer?.start()
            statusTextView.text = "Estado: Reproduciendo"

            val songTitle = getSongTitle(currentSongIndex)
            Toast.makeText(this, "Reproduciendo: $songTitle", Toast.LENGTH_SHORT).show()

            // Iniciar la actualización de la SeekBar
            startSeekBarUpdate()
        }

        // Configurar el botón de pausa
        pauseButton.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
                statusTextView.text = "Estado: Pausado"
                Toast.makeText(this, "Música pausada", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar el botón de detener
        stopButton.setOnClickListener {
            if (mediaPlayer != null) {
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
                statusTextView.text = "Estado: Detenido"
                Toast.makeText(this, "Reproducción detenida", Toast.LENGTH_SHORT).show()

                // Detener la actualización de la SeekBar
                handler.removeCallbacks(runnable)
                seekBar.progress = 0
                currentTimeTextView.text = "0:00"
            }
        }

        // Configurar el botón de canción anterior
        prevButton.setOnClickListener {
            changeSong(false)
        }

        // Configurar el botón de canción siguiente
        nextButton.setOnClickListener {
            changeSong(true)
        }

        // Configurar la SeekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                    updateCurrentTimeText(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // No es necesario implementar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // No es necesario implementar
            }
        })

        // Definir el runnable para actualizar la SeekBar
        runnable = Runnable {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    val currentPosition = it.currentPosition
                    seekBar.progress = currentPosition
                    updateCurrentTimeText(currentPosition)
                    handler.postDelayed(runnable, 1000)
                }
            }
        }
    }

    private fun loadAllSongMetadata() {
        // Limpiar lista anterior
        songMetadata.clear()

        // Cargar metadatos para cada canción
        for (songId in songList) {
            val metadata = extractMetadata(songId)
            songMetadata.add(metadata)
        }
    }

    private fun extractMetadata(resourceId: Int): Map<String, String> {
        val metadataRetriever = MediaMetadataRetriever()
        val metadata = mutableMapOf<String, String>()

        try {
            // Obtener descriptor de archivo para el recurso raw
            val afd: AssetFileDescriptor = resources.openRawResourceFd(resourceId)
            metadataRetriever.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            afd.close()

            // Extraer metadatos
            val title = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            val artist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            val album = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)

            // Guardar metadatos extraídos
            metadata["title"] = title ?: "Canción desconocida"
            metadata["artist"] = artist ?: "Artista desconocido"
            metadata["album"] = album ?: "Álbum desconocido"

        } catch (e: Exception) {
            // En caso de error, usar nombres predeterminados
            metadata["title"] = "Canción ${songList.indexOf(resourceId) + 1}"
            metadata["artist"] = "Artista desconocido"
            metadata["album"] = "Álbum desconocido"
            e.printStackTrace()
        } finally {
            metadataRetriever.release()
        }

        return metadata
    }

    private fun getSongTitle(index: Int): String {
        return if (index < songMetadata.size) {
            songMetadata[index]["title"] ?: "Canción desconocida"
        } else {
            "Canción desconocida"
        }
    }

    private fun getArtistName(index: Int): String {
        return if (index < songMetadata.size) {
            songMetadata[index]["artist"] ?: "Artista desconocido"
        } else {
            "Artista desconocido"
        }
    }

    private fun updateSongInfo() {
        val songTitleTextView = findViewById<TextView>(R.id.songTitleTextView)
        val artistTextView = findViewById<TextView>(R.id.artistTextView)
        val albumCoverImageView = findViewById<ImageView>(R.id.albumCoverImageView)

        // Obtener título y artista de los metadatos
        val title = getSongTitle(currentSongIndex)
        val artist = getArtistName(currentSongIndex)

        // Actualizar UI
        songTitleTextView.text = title
        artistTextView.text = artist

        // Actualizar portada
        if (currentSongIndex < albumCovers.size) {
            albumCoverImageView.setImageResource(albumCovers[currentSongIndex])
        }
    }

    private fun prepareMediaPlayer() {
        // Liberar MediaPlayer previo si existe
        releaseMediaPlayer()

        // Crear un nuevo MediaPlayer
        mediaPlayer = MediaPlayer.create(this, songList[currentSongIndex])

        // Configurar un listener para cuando la reproducción termine
        mediaPlayer?.setOnCompletionListener {
            val statusTextView = findViewById<TextView>(R.id.statusTextView)
            statusTextView.text = "Estado: Completado"

            // Opcionalmente, cambiar automáticamente a la siguiente canción
            // changeSong(true)
        }

        // Configurar la SeekBar
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val totalTimeTextView = findViewById<TextView>(R.id.totalTimeTextView)

        mediaPlayer?.let {
            seekBar.max = it.duration
            totalTimeTextView.text = formatTime(it.duration)
        }
    }

    private fun changeSong(next: Boolean) {
        // Calcular el nuevo índice
        if (next) {
            currentSongIndex = (currentSongIndex + 1) % songList.size
        } else {
            currentSongIndex = if (currentSongIndex > 0) currentSongIndex - 1 else songList.size - 1
        }

        // Actualizar información de la canción
        updateSongInfo()

        // Si estaba reproduciendo, reiniciar con la nueva canción
        val wasPlaying = mediaPlayer?.isPlaying ?: false
        prepareMediaPlayer()
        if (wasPlaying) {
            mediaPlayer?.start()
            startSeekBarUpdate()
        }

        // Actualizar estado
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        statusTextView.text = if (wasPlaying) "Estado: Reproduciendo" else "Estado: Listo"

        Toast.makeText(this, "Cambiado a: ${getSongTitle(currentSongIndex)}", Toast.LENGTH_SHORT).show()
    }

    private fun startSeekBarUpdate() {
        handler.postDelayed(runnable, 0)
    }

    private fun updateCurrentTimeText(position: Int) {
        val currentTimeTextView = findViewById<TextView>(R.id.currentTimeTextView)
        currentTimeTextView.text = formatTime(position)
    }

    private fun formatTime(milliseconds: Int): String {
        val minutes = milliseconds / 60000
        val seconds = (milliseconds % 60000) / 1000
        return "$minutes:${if (seconds < 10) "0$seconds" else "$seconds"}"
    }

    private fun releaseMediaPlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    // Es importante liberar recursos cuando la actividad se destruye
    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}