import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.challangechap6_fikrihaikal.di.LocalModule
import com.example.challangechap6_fikrihaikal.model.data.local.AppDatabase
import com.example.challangechap6_fikrihaikal.model.data.local.dao.FavoriteDao
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalModuleTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var favoriteDao: FavoriteDao

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        favoriteDao = appDatabase.favoriteDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun testProvidesDatabaseShouldReturnNonNullAppDatabaseInstance() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val database = LocalModule.providesDatabase(context)

        assertNotNull(database)
    }

    @Test
    fun testProvideFavoriteDaoShouldReturnNonNullFavoriteDaoInstance() {
        val dao = LocalModule.provideFavoriteDao(appDatabase)

        assertNotNull(dao)
    }
}
