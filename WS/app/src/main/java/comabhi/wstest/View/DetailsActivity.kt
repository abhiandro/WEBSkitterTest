package comabhi.wstest.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import comabhi.wstest.R
import comabhi.wstest.databinding.ActivityDetailsBinding
import io.paperdb.Paper

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var name =   Paper.book().read("Name","")
        var email = Paper.book().read("Email","")
        var imgUrl = Paper.book().read("ImageUrl","")
        Picasso.get().load(imgUrl).into(binding.imageView);
        binding.name.setText(""+name)
        binding.tvEmail.setText(""+email)
    }
}