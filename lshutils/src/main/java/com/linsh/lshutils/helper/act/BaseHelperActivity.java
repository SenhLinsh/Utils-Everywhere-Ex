package com.linsh.lshutils.helper.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    date   : 2018/02/04
 *    desc   :
 * </pre>
 */
public class BaseHelperActivity extends AppCompatActivity {

    private List<IActivityHelper> mAssistants;

    protected void addAssistant(@NonNull IActivityHelper assistant) {
        if (mAssistants == null) mAssistants = new ArrayList<>();
        mAssistants.add(assistant);
    }

    protected void addAssistant(@NonNull IActivityHelper... assistants) {
        if (mAssistants == null) mAssistants = new ArrayList<>();
        mAssistants.addAll(Arrays.asList(assistants));
    }

    protected void removeAssistant(@NonNull IActivityHelper assisted) {
        if (mAssistants != null) {
            mAssistants.remove(assisted);
        }
    }

    protected void removeAllAssistants() {
        if (mAssistants != null) {
            mAssistants.clear();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onCreate(savedInstanceState);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onStart();
            }
        }
    }

    protected void onResume() {
        super.onResume();
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onResume();
            }
        }
    }

    protected void onPause() {
        super.onPause();
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onPause();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onStop();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onDestroy();
            }
        }
        removeAllAssistants();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onSaveInstanceState(outState);
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onRestoreInstanceState(savedInstanceState);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onAttachedToWindow();
            }
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onDetachedFromWindow();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                assistant.onNewIntent(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = false;
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                result |= assistant.onCreateOptionsMenu(menu);
            }
        }
        return super.onCreateOptionsMenu(menu) || result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = false;
        if (mAssistants != null) {
            for (IActivityHelper assistant : mAssistants) {
                result |= assistant.onOptionsItemSelected(item);
            }
        }
        return super.onOptionsItemSelected(item) || result;
    }
}
