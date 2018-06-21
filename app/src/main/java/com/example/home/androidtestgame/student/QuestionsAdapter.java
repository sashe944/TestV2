package com.example.home.androidtestgame.student;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.objects.Question;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private static final int QUESTION_TYPE_SA = 1;
    private static final int QUESTION_TYPE_MA = 2;
    private static final int QUESTION_TYPE_FT = 3;

    private List<Question> questions;

    public QuestionsAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public int getItemViewType(int position) {
        return (int) questions.get(position).questionTypeId;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case QUESTION_TYPE_MA:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_question_checkbox, parent, false);
                return new CheckboxQuestionViewHolder(itemView);
            case QUESTION_TYPE_SA:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_question_radiobtn, parent, false);
                return new RadioBtnQuestionViewHolder(itemView);
            case QUESTION_TYPE_FT:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_question_freetext, parent, false);
                return new FreeTextQuestionViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.tvQuestionName.setText(question.name);
        switch ((int) question.questionTypeId) {
            case QUESTION_TYPE_MA:
                CheckboxQuestionViewHolder hMa = (CheckboxQuestionViewHolder) holder;
                hMa.cb1.setText(question.getPossibleAnswers().get(0).name);
                hMa.cb2.setText(question.getPossibleAnswers().get(1).name);
                hMa.cb3.setText(question.getPossibleAnswers().get(2).name);
                break;
            case QUESTION_TYPE_SA:
                RadioBtnQuestionViewHolder hSa = (RadioBtnQuestionViewHolder) holder;
                 if (question.possibleAnswers.size()>0)
                    hSa.rb1.setText(question.getPossibleAnswers().get(0).name);
                if (question.possibleAnswers.size()>1)
                    hSa.rb2.setText(question.getPossibleAnswers().get(1).name);
                if (question.possibleAnswers.size()>2)
                    hSa.rb3.setText(question.getPossibleAnswers().get(2).name);
                break;
            case QUESTION_TYPE_FT:
                // do nothing
                break;
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    class FreeTextQuestionViewHolder extends QuestionViewHolder {
        EditText etAnswer;

        public FreeTextQuestionViewHolder(View itemView) {
            super(itemView);
            etAnswer = itemView.findViewById(R.id.etAnswer);
        }
    }

    class RadioBtnQuestionViewHolder extends QuestionViewHolder {
        RadioButton rb1, rb2, rb3;

        public RadioBtnQuestionViewHolder(View itemView) {
            super(itemView);
            rb1 = itemView.findViewById(R.id.rb1);
            rb2 = itemView.findViewById(R.id.rb2);
            rb3 = itemView.findViewById(R.id.rb3);
        }
    }

    class CheckboxQuestionViewHolder extends QuestionViewHolder {
        CheckBox cb1, cb2, cb3;

        public CheckboxQuestionViewHolder(View itemView) {
            super(itemView);
            cb1 = itemView.findViewById(R.id.cb1);
            cb2 = itemView.findViewById(R.id.cb2);
            cb3 = itemView.findViewById(R.id.cb3);
        }
    }


    class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestionName;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            tvQuestionName = itemView.findViewById(R.id.tvQuestionName);
        }
    }
}
