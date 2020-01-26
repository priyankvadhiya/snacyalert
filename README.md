# Snacyalert For Android 

<img src="https://github.com/priyankvadhiya/snacyalert/blob/master/sa.gif" width="250">

## Usage

Step:1 Add it in your root build.gradle at the end of repositories

    allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
    }

Step 2: Add the dependency

    dependencies {
            implementation 'com.github.priyankvadhiya:snacyalert:1.0'
    }

Step 3: show snacyalert

    SnacyAlert.create(this)
                .setTitle("Title")
                .setText("Text...")
                .setBackgroundColorInt(Color.GREEN)
                .setIcon(R.drawable.ic_notifications_black_24dp)
                .setDuration(2000)
                .showIcon(true)
                .show()
