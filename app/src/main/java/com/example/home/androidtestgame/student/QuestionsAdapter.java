package com.example.home.androidtestgame.student;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.objects.PossibleAnswer;
import com.example.home.androidtestgame.objects.Question;
import com.example.home.androidtestgame.objects.StudentAnswer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private static final int QUESTION_TYPE_SA = 1;
    private static final int QUESTION_TYPE_MA = 2;
    private static final int QUESTION_TYPE_FT = 3;

    private List<Question> questions;
    private List<StudentAnswer> answers;
    private Map<Long, StudentAnswer> studentFreeTexAnswers = new HashMap<>();

    public QuestionsAdapter(List<Question> questions) {
        this.questions = questions;
        answers = new ArrayList<>(questions.size());
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
        final Question question = questions.get(position);
        holder.tvQuestionName.setText(question.name);
        final PossibleAnswer possibleAnswer0 = question.getPossibleAnswers().get(0);
        final PossibleAnswer possibleAnswer1 = question.getPossibleAnswers().get(1);
        final PossibleAnswer possibleAnswer2 = question.getPossibleAnswers().get(2);
        switch ((int) question.questionTypeId) {
            case QUESTION_TYPE_MA:
                CheckboxQuestionViewHolder hMa = (CheckboxQuestionViewHolder) holder;
                hMa.cb1.setText(possibleAnswer0.name);
                hMa.cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            StudentAnswer answer = new StudentAnswer();
                            answer.possibleAnswerId = possibleAnswer0.id;
                            answer.questioId = question.id;
                            answers.add(answer);
                        } else {
                            long possibleAnswerId = possibleAnswer0.id;
                            int matchIndex = -1;
                            for (int i = 0; i < answers.size(); i++) {
                                StudentAnswer answer = answers.get(i);
                                if(answer.possibleAnswerId == possibleAnswerId) {
                                    matchIndex = i;
                                }
                            }
                            if (matchIndex >-1) answers.remove(matchIndex);
                        }
                    }
                });


                hMa.cb2.setText(possibleAnswer1.name);
                hMa.cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            StudentAnswer answer = new StudentAnswer();
                            answer.possibleAnswerId = possibleAnswer1.id;
                            answer.questioId = question.id;
                            answers.add(answer);
                        } else {
                            long possibleAnswerId = possibleAnswer1.id;
                            int matchIndex = -1;
                            for (int i = 0; i < answers.size(); i++) {
                                StudentAnswer answer = answers.get(i);
                                if(answer.possibleAnswerId == possibleAnswerId) {
                                    matchIndex = i;
                                }
                            }
                            if (matchIndex >-1) answers.remove(matchIndex);
                        }
                    }
                });

                hMa.cb3.setText(possibleAnswer2.name);
                hMa.cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            StudentAnswer answer = new StudentAnswer();
                            answer.possibleAnswerId = possibleAnswer2.id;
                            answer.questioId = question.id;
                            answers.add(answer);
                        } else {
                            long possibleAnswerId = possibleAnswer2.id;
                            int matchIndex = -1;
                            for (int i = 0; i < answers.size(); i++) {
                                StudentAnswer answer = answers.get(i);
                                if(answer.possibleAnswerId == possibleAnswerId) {
                                    matchIndex = i;
                                }
                            }
                            if (matchIndex >-1) answers.remove(matchIndex);
                        }
                    }
                });
                break;
            case QUESTION_TYPE_SA:
                RadioBtnQuestionViewHolder hSa = (RadioBtnQuestionViewHolder) holder;
                 if (question.possibleAnswers.size()>0)
                    hSa.rb1.setText(possibleAnswer0.name);
                hSa.rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            StudentAnswer answer = new StudentAnswer();
                            answer.possibleAnswerId = possibleAnswer0.id;
                            answer.questioId = question.id;
                            answers.add(answer);
                        } else {
                            long possibleAnswerId = possibleAnswer0.id;
                            int matchIndex = -1;
                            for (int i = 0; i < answers.size(); i++) {
                                StudentAnswer answer = answers.get(i);
                                if(answer.possibleAnswerId == possibleAnswerId) {
                                    matchIndex = i;
                                }
                            }
                            if (matchIndex >-1) answers.remove(matchIndex);
                        }
                    }
                });

                if (question.possibleAnswers.size()>1)
                    hSa.rb2.setText(possibleAnswer1.name);
                hSa.rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            StudentAnswer answer = new StudentAnswer();
                            answer.possibleAnswerId = possibleAnswer1.id;
                            answer.questioId = question.id;
                            answers.add(answer);
                        } else {
                            long possibleAnswerId = possibleAnswer1.id;
                            int matchIndex = -1;
                            for (int i = 0; i < answers.size(); i++) {
                                StudentAnswer answer = answers.get(i);
                                if(answer.possibleAnswerId == possibleAnswerId) {
                                    matchIndex = i;
                                }
                            }
                            if (matchIndex >-1) answers.remove(matchIndex);
                        }
                    }
                });
                if (question.possibleAnswers.size()>2)
                    hSa.rb3.setText(possibleAnswer2.name);
                hSa.rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            StudentAnswer answer = new StudentAnswer();
                            answer.possibleAnswerId = possibleAnswer2.id;
                            answer.questioId = question.id;
                            answers.add(answer);
                        } else {
                            long possibleAnswerId = possibleAnswer2.id;
                            int matchIndex = -1;
                            for (int i = 0; i < answers.size(); i++) {
                                StudentAnswer answer = answers.get(i);
                                if(answer.possibleAnswerId == possibleAnswerId) {
                                    matchIndex = i;
                                }
                            }
                            if (matchIndex >-1) answers.remove(matchIndex);
                        }
                    }
                });
                break;
            case QUESTION_TYPE_FT:
                // do nothing
                FreeTextQuestionViewHolder fTh = (FreeTextQuestionViewHolder) holder;
                fTh.etAnswer.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (studentFreeTexAnswers.containsKey(question.id)) {
                            studentFreeTexAnswers.get(question.id).freeText = editable.toString();
                        } else {
                            StudentAnswer sa = new StudentAnswer();
                            sa.freeText = editable.toString();
                            sa.questioId = question.id;
                            studentFreeTexAnswers.put(question.id, sa);
                        }
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public List<StudentAnswer> getAnswers() {
        return answers;
    }

    public Map<Long, StudentAnswer> getStudentFreeTexAnswers() {
        return studentFreeTexAnswers;
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
