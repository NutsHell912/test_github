package com.test.nutshell.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.test.nutshell.R;
import com.test.nutshell.base.BaseActivity;
import com.test.nutshell.search.SearchActivity;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StartActivity extends BaseActivity implements StartMVP.View {

    private Unbinder unbinder;
    @Inject
    StartMVP.Presenter<StartMVP.View, StartMVP.Interactor> presenter;

    @BindView(R.id.login)
    EditText login;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login_progress)
    ProgressBar progressBar;
    @BindView(R.id.sign_in_button)
    Button signInButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.setDebug(true);
        getActivityComponent().inject(this);
        unbinder = ButterKnife.bind(this);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.GONE);
        signInButton.setEnabled(true);
        //setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSignInButtonClicked();
            }
        });
        login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.onLoginChaned(s.toString());
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.onPasswordChanged(s.toString());
            }
        });


        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    presenter.onSignInButtonClicked();
                    return true;
                }
                return false;
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            presenter.onBackClick();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAttach(this);
        disableProgress();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onDetach();
    }

    @Override
    public void navigateToSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void renderProgress() {
        progressBar.setVisibility(View.VISIBLE);
        signInButton.setEnabled(false);
        login.setEnabled(false);
        password.setEnabled(false);

    }

    @Override
    public void showAuthError() {
        disableProgress();
        password.requestFocus();
        password.setError("Wrong username or password");
    }

    @Override
    public void renderLogin(@NonNull String login) {
        this.login.setText(login);
    }

    @Override
    public void renderPassword(@NonNull String password) {
        this.password.setText(password);
    }

    @Override
    public void showError(@NonNull String msg) {
        disableProgress();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void disableProgress() {
        progressBar.setVisibility(View.GONE);
        signInButton.setEnabled(true);
        login.setEnabled(true);
        password.setEnabled(true);
    }
}
