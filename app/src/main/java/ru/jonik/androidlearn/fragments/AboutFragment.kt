import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.jonik.androidlearn.BuildConfig
import ru.jonik.androidlearn.R
import ru.jonik.androidlearn.databinding.FragmentAboutBinding
import ru.jonik.androidlearn.fragments.contract.HasCustomTitle
import ru.jonik.androidlearn.fragments.contract.navigator

class AboutFragment : Fragment(), HasCustomTitle {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentAboutBinding.inflate(inflater, container, false).apply {
        versionNameTextView.text = BuildConfig.VERSION_NAME
        versionCodeTextView.text = BuildConfig.VERSION_CODE.toString()
        okButton.setOnClickListener { onOkPressed() }
    }.root

    override fun getTitleRes(): Int = R.string.about

    private fun onOkPressed() {
        navigator().goBack()
    }

}