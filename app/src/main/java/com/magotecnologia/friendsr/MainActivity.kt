package com.magotecnologia.friendsr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.magotecnologia.friendsr.DetailActivity.Companion.FRIEND_EXTRA
import com.magotecnologia.friendsr.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), PeopleAdapter.PeopleListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val friends = mutableListOf<Friend>(
            Friend(
                "Chandler",
                "Chandler Muriel Bing nació el 8 de abril de 1968, hijo de la novelista erótica Nora Bing (Morgan Fairchild en cuatro episodios) y de Charles Bing (Kathleen Turner) una travesti que trabaja en Las Vegas. Chandler fue compañero de habitación de Ross Geller en la universidad. Chandler es medio escocés.2\u200B Chandler conoció a la hermana de Ross, Monica Geller, y su amiga Rachel Green durante la celebración del Día de Acción de Gracias en la casa de la familia Geller durante su primer año en la universidad.",
                "https://vignette.wikia.nocookie.net/friends/images/7/70/Chandler_Bing.jpg/revision/latest?cb=20120423114518&path-prefix=es",
                0F
            )
        )
        friends.add(
            Friend(
                "Joey",
                "Joey es un actor \"estereotipo\": tiene mucho sexo, tiene un bajo nivel de educación y está constantemente buscando trabajo. Fue ordenado sacerdote a través de internet en \"The One with the Truth About London\", y ofició en las bodas de Chandler y Monica, y Phoebe y Mike. ",
                "https://vignette.wikia.nocookie.net/friends/images/5/5a/Square_Joey.jpg/revision/latest?cb=20160528174812",
                0F
            )
        )
        friends.add(
            Friend(
                "Monica",
                "A pesar de su carácter extremadamente competitivo y su obsesión con la limpieza, es un personaje carismático y espontáneo, la más centrada del grupo y esta dispuesta en ayudar a sus amigos y no deja de ser una persona alegre y de buen corazón ",
                "https://vignette.wikia.nocookie.net/friends/images/6/67/Magalytv-05-courteney-cox.jpg/revision/latest?cb=20090611172643&path-prefix=es",
                0F
            )
        )
        friends.add(
            Friend(
                "Phoebe",
                "Phoebe es una guitarrista de carácter dulce pero algo extraña. Al no haber experimentado una infancia normal y completa, Phoebe es a menudo muy tonta, a veces inteligente y, en algunos aspectos, tiene un buen corazón.",
                "https://vignette.wikia.nocookie.net/friends/images/a/a7/Phoebe_Buffay.jpg/revision/latest?cb=20120423114554&path-prefix=es",
                0F
            )
        )
        friends.add(
            Friend(
                "Ross",
                "Ross Geller tiene un Doctorado en paleontología, con esto da a notar que tiene una fascinación por los dinosaurios y gracias a este nivel academico da clases en la universidad.",
                "https://vignette.wikia.nocookie.net/friends/images/f/f2/Ross_Geller.jpg/revision/latest?cb=20120423114622&path-prefix=es",
                0F
            )
        )
        friends.add(
            Friend(
                "Ross",
                "Los padres de Rachel son ricos y generalmente cínicos en su intento de llevar una vida independiente en la ciudad, en la mitad de la serie se divorcian y esto molesta mucho a Rachel. Su madre, interpretada por Marlo Thomas, es cómicamente esnob, y su padre, interpretado por Ron Leibman, es severo e intimidante, aunque se revela en “The One With Rachel's Sister” que está orgulloso del éxito de Rachel de vivir independiente.",
                "https://vignette.wikia.nocookie.net/friends/images/5/58/Rachel_Green.jpg/revision/latest?cb=20120423114129&path-prefix=es",
                0F
            )
        )
        val adapter = PeopleAdapter(friends)
        adapter.setOnFriendListener(this)
        binding.rvPeople.adapter = adapter
    }

    override fun onFriendClick(friend: Friend) {
        super.onFriendClick(friend)
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(FRIEND_EXTRA, friend)
        startActivityForResult(intent, DetailActivity.DETAIL_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DetailActivity.DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                if (it.hasExtra(FRIEND_EXTRA)) {
                    val updateFriend = it.getParcelableExtra(FRIEND_EXTRA) as Friend
                    updateFriendAtRecycler(updateFriend)
                    Toast.makeText(
                        applicationContext,
                        "Se actualizo a ${updateFriend.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun updateFriendAtRecycler(updateFriend: Friend) {
        (rv_people.adapter as PeopleAdapter).updatePerson(friend = updateFriend)
    }
}
