package com.mehedi.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionCollection extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA, optionB, optionC, optionD;
    Button confirm;
    String rightAnswer, Answer;
    public static List<QuestionModule> question_list = new ArrayList<>();
    int score;
    public static String SUBJECT_NAME = "";
    public static ArrayList<ArrayList<QuestionModule>> questionBank = new ArrayList<>();
    public static ArrayList<HashMap<String, String>> subjectList = new ArrayList<>();
    LinearLayout rootLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        rootLay = findViewById(R.id.rootLay);
        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.opcaoA);
        optionB = findViewById(R.id.opcaoB);
        optionC = findViewById(R.id.opcaoC);
        optionD = findViewById(R.id.opcaoD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);

        loadQuestion();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadQuestion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (rootLay != null) {
            rootLay.startAnimation(AnimationUtils.loadAnimation(this, R.anim.middle_to_top));
        }
    }

    @Override
    public void onBackPressed() {
        // Prevent back press functionality
    }

    private void loadQuestion() {
        if (question_list == null || question_list.isEmpty()) {
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        } else {
            QuestionModule q = question_list.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));
            rightAnswer = q.getRightAnswer();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        if (op == R.id.opcaoA) {
            Answer = "A";
        } else if (op == R.id.opcaoB) {
            Answer = "B";
        } else if (op == R.id.opcaoC) {
            Answer = "C";
        } else if (op == R.id.opcaoD) {
            Answer = "D";
        } else {
            return; // Exit if no option is selected
        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));
    }


    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, RightActivity.class);

        }else {
            screen = new Intent(this, WrongActivity.class);
        }

        return screen;
    }



    //===============================================================================================







    //====================================================================
    //====================================================================
    public static  ArrayList <QuestionModule> questions;
    public static void createQuestionBank(){
        QuestionCollection.subjectList = new ArrayList<>();
        QuestionCollection.questionBank = new ArrayList<>();



        //------------- Subject 1
        questions = new ArrayList(){
            {
                add(new QuestionModule("Which poetic device is used in the phrase 'The wind whispered through the trees'?", "C", "Hyperbole", "Onomatopoeia", "Personification", "Metaphor"));
                add(new QuestionModule("What is the correct form of the verb in 'If he ___ (study) harder, he would have passed the exam'?", "B", "studies", "had studied", "was studying", "study"));
                add(new QuestionModule("Identify the type of clause in: 'I know where he lives.'", "D", "Adjective Clause", "Independent Clause", "Noun Clause", "Adverb Clause"));
                add(new QuestionModule("Choose the synonym for 'Erudite'.", "A", "Learned", "Ignorant", "Clueless", "Artful"));
                add(new QuestionModule("What is the antonym of 'Ephemeral'?", "B", "Temporary", "Permanent", "Transitory", "Fleeting"));
                add(new QuestionModule("Which sentence is grammatically correct?", "C", "She don’t like ice cream.", "He go to school every day.", "Everyone has a role to play.", "They was late."));
                add(new QuestionModule("Identify the figure of speech: 'I am so hungry I could eat a horse.'", "A", "Hyperbole", "Alliteration", "Simile", "Irony"));
                add(new QuestionModule("Which one is a Complex Sentence?", "B", "I like apples and oranges.", "Although he was tired, he kept working.", "He is a good boy.", "She went home early."));
                add(new QuestionModule("What is the meaning of the idiom 'Burning the midnight oil'?", "D", "Wasting time", "Sleeping early", "Cooking late at night", "Working late into the night"));
                add(new QuestionModule("Which word correctly completes the sentence: 'Despite the ___ weather, we decided to go hiking.'?", "A", "inclement", "inclusive", "pleasant", "intense"));

            }
        };
        QuestionModule.createQuestionsForSubject("English", R.drawable.eng, questions);



        //------------- Subject 2
        questions = new ArrayList(){
            {
                // Bangla Questions
                add(new QuestionModule("বাংলাদেশের জাতীয় ফুল কোনটি?", "B", "গোলাপ", "শাপলা", "চামেলি", "রজনীগন্ধা"));
                add(new QuestionModule("বাংলাদেশের স্বাধীনতার ঘোষণা কত তারিখে হয়?", "A", "২৬ মার্চ ১৯৭১", "১৬ ডিসেম্বর ১৯৭১", "২১ ফেব্রুয়ারি ১৯৫২", "৭ মার্চ ১৯৭১"));

       // English Questions
                add(new QuestionModule("What is the synonym of 'Happy'?", "C", "Sad", "Angry", "Joyful", "Lonely"));
                add(new QuestionModule("Which part of speech describes an action?", "A", "Verb", "Noun", "Adjective", "Pronoun"));

           // Math Questions
                add(new QuestionModule("What is the square of 12?", "B", "122", "144", "132", "120"));
                add(new QuestionModule("Solve: 5x + 3 = 18. Find x.", "D", "3", "4", "5", "3"));

            // Physics Questions
                add(new QuestionModule("What is the speed of light in vacuum?", "C", "1,50,000 km/s", "2,50,000 km/s", "3,00,000 km/s", "3,50,000 km/s"));
                add(new QuestionModule("What is the SI unit of force?", "A", "Newton", "Joule", "Watt", "Kilogram"));

         // Mixed Questions
                add(new QuestionModule("রক্তের কোন উপাদান বেশি থাকে?", "C", "RBC", "WBC", "প্লাজমা", "হিমোগ্লোবিন"));
                add(new QuestionModule("Who discovered gravity?", "B", "Einstein", "Newton", "Galileo", "Faraday"));

            }
        };
        QuestionModule.createQuestionsForSubject("বিসিএস", R.drawable.bcs, questions);







        //------------- Subject 3
        questions = new ArrayList(){
            {

                add(new QuestionModule("নিউটনের প্রথম গতি সূত্র কী বোঝায়?", "B", "বল সংরক্ষণ", "জড়তার নীতি", "গতি পরিবর্তন", "গতি ত্বরণ"));
                add(new QuestionModule("কোনটি ভরের একক?", "C", "নিউটন", "গতি", "কিলোগ্রাম", "জুল"));
                add(new QuestionModule("আলোর গতি প্রায় কত?", "D", "1,00,000 কিমি/সেকেন্ড", "2,50,000 কিমি/সেকেন্ড", "3,00,000 কিমি/মিনিট", "3,00,000 কিমি/সেকেন্ড"));
                add(new QuestionModule("তড়িৎ চৌম্বক তত্ত্বের জনক কে?", "A", "ম্যাক্সওয়েল", "নিউটন", "আইনস্টাইন", "ওম"));
                add(new QuestionModule("ধ্বনির গতি কোন মাধ্যমে সবচেয়ে বেশি?", "C", "বায়ু", "পানি", "কঠিন পদার্থ", "গ্যাস"));
                add(new QuestionModule("গ্রহ এবং সূর্যের মধ্যবর্তী বল কোন বলের কারণে কাজ করে?", "A", "মহাকর্ষীয় বল", "তড়িৎ বল", "যান্ত্রিক বল", "চৌম্বক বল"));
                add(new QuestionModule("তাপমাত্রার এসআই একক কী?", "B", "সেলসিয়াস", "কেলভিন", "ফারেনহাইট", "জুল"));
                add(new QuestionModule("সমবেগ গতিতে চলা বস্তুর জন্য কোনটি সত্য?", "A", "ত্বরণ শূন্য", "ত্বরণ ধনাত্মক", "ত্বরণ ঋণাত্মক", "পদার্থের ওজন বাড়ে"));
                add(new QuestionModule("ওহমের সূত্র কোনটির উপর নির্ভরশীল?", "B", "তাপমাত্রা", "প্রথম ও দ্বিতীয় উভয়টি", "অভিযোজিত বল", "চুম্বকত্ব"));
                add(new QuestionModule("পদার্থের স্থিতিস্থাপকতার জন্য কোনটি প্রযোজ্য?", "C", "হুকের সূত্র", "বয়েলের সূত্র", "স্থিতিস্থাপকতার সূত্র", "তড়িৎ চৌম্বক সূত্র"));
            }
        };
        QuestionModule.createQuestionsForSubject("পদার্থ বিজ্ঞান", R.drawable.category_icon3, questions);



        //------------- Subject 4
        questions = new ArrayList(){
            {
                add(new QuestionModule("কোন যৌগটি পলিমার নয়?", "D", "পলিথিন", "নাইলন", "টেফলন", "বেনজিন"));
                add(new QuestionModule("পিএইচ স্কেলে কোনটির মান বেশি হবে?", "B", "অম্ল", "ক্ষার", "জল", "সক্রিয় হাইড্রোজেন"));
                add(new QuestionModule("অ্যাসিডের পানিতে দ্রবণীয়তা কী নির্ভর করে?", "C", "তাপমাত্রা", "চাপ", "দ্রাবক ধরণ", "পানির মাত্রা"));
                add(new QuestionModule("কোন গ্যাস গ্রিনহাউস গ্যাস হিসেবে পরিচিত?", "C", "নাইট্রোজেন", "অক্সিজেন", "কার্বন ডাই অক্সাইড", "হাইড্রোজেন"));
                add(new QuestionModule("রসায়নে ‘মোল’ শব্দটি কী বোঝায়?", "A", "৬.০২ × ১০^২৩ কণা", "শক্তি", "চাপ", "আয়তন"));
                add(new QuestionModule("কোন ধাতুটি প্রকৃতিতে তরল অবস্থায় থাকে?", "B", "সীসা", "পারদ", "অ্যালুমিনিয়াম", "টাইটানিয়াম"));
                add(new QuestionModule("কোন প্রক্রিয়ায় আয়রন মরিচা ধরে?", "C", "জারণ", "পুনঃস্থিতি", "অক্সিডেশন", "বাষ্পীয়করণ"));
                add(new QuestionModule("রসায়নের সৃষ্ট প্রক্রিয়ায় শক্তি কত প্রকার?", "B", "১ প্রকার", "২ প্রকার", "৩ প্রকার", "অনির্ধারিত"));
                add(new QuestionModule("হিলিয়াম কোন গ্যাস গ্রুপের অন্তর্ভুক্ত?", "D", "আলোকোজ্জ্বল গ্যাস", "জ্বালানি গ্যাস", "অম্লীয় গ্যাস", "নির্জীব গ্যাস"));
                add(new QuestionModule("সর্বাধিক ইলেকট্রোনেগেটিভ মৌল কোনটি?", "A", "ফ্লোরিন", "অক্সিজেন", "নাইট্রোজেন", "ক্লোরিন"));


            }
        };
        QuestionModule.createQuestionsForSubject("রসায়ন", R.drawable.category_icon4, questions);






        //------------- Subject 5
        questions = new ArrayList(){
            {
                add(new QuestionModule("কোনটি জীবনের মৌলিক একক?", "B", "টিস্যু", "কোষ", "অঙ্গ", "জিন"));
                add(new QuestionModule("কোষের শক্তি উৎপাদন কেন্দ্র কোনটি?", "B", "ক্লোরোপ্লাস্ট", "মাইটোকন্ড্রিয়া", "নিউক্লিয়াস", "রাইবোসোম"));
                add(new QuestionModule("মানবদেহের প্রধান শক্তির উৎস কী?", "A", "কার্বোহাইড্রেট", "প্রোটিন", "চর্বি", "ভিটামিন"));
                add(new QuestionModule("কোন অঙ্গ দেহের ভারসাম্য রক্ষা করে?", "C", "হৃদপিণ্ড", "ফুসফুস", "মস্তিষ্ক", "কিডনি"));
                add(new QuestionModule("DNA কী সংরক্ষণ করে?", "C", "শক্তি", "প্রোটিন", "জিনগত তথ্য", "রক্তকণা"));
                add(new QuestionModule("মানবদেহের কোন অঙ্গটি অক্সিজেন শোষণ করে?", "B", "লিভার", "ফুসফুস", "হৃদপিণ্ড", "কিডনি"));
                add(new QuestionModule("কোনটি সালোকসংশ্লেষণের জন্য প্রয়োজনীয়?", "C", "অক্সিজেন", "নাইট্রোজেন", "কার্বন ডাই অক্সাইড", "হাইড্রোজেন"));
                add(new QuestionModule("কোন অঙ্গ জলের পরিশোধন করে?", "A", "কিডনি", "হৃদপিণ্ড", "যকৃত", "প্লীহা"));
                add(new QuestionModule("মানবদেহের বৃহত্তম অঙ্গ কোনটি?", "C", "লিভার", "হাড়", "ত্বক", "ফুসফুস"));
                add(new QuestionModule("রক্তের প্রধান উপাদান কোনটি?", "C", "RBC", "WBC", "প্লাজমা", "হিমোগ্লোবিন"));

            }
        };
        QuestionModule.createQuestionsForSubject("জীব বিজ্ঞান", R.drawable.category_icon5, questions);



        //------------- Subject 6
        questions = new ArrayList(){
            {add(new QuestionModule("একটি সংখ্যা এবং তার বর্গের যোগফল ২০। সংখ্যাটি কত?", "B", "৪", "৫", "৩", "২"));
                add(new QuestionModule("ত্রিভুজের তিনটি বাহু ৩ সেমি, ৪ সেমি ও ৫ সেমি। এর ক্ষেত্রফল কত?", "C", "৫ বর্গ সেমি", "৬ বর্গ সেমি", "৬ বর্গ একক", "৪ বর্গ একক"));
                add(new QuestionModule("logₐ(xy) কে কীভাবে লেখা যায়?", "A", "logₐx + logₐy", "logₐx - logₐy", "xlogₐy", "logₐy - logₐx"));
                add(new QuestionModule("দুইটি সংখ্যার গ.সা.গু. ১২ এবং ল.সা.গু. ৭২। সংখ্যা দুইটির গুণফল কত?", "D", "৮০", "৬০", "১০০", "৮৬৪"));
                add(new QuestionModule("(3x + 5)² সমীকরণটির বিস্তার কী হবে?", "B", "9x² + 15x + 25", "9x² + 30x + 25", "12x² + 15x + 10", "8x² + 20x + 16"));
                add(new QuestionModule("π এর মানের প্রথম তিনটি অংক কী?", "A", "৩.১৪", "৩.১৫", "৩.১৩", "৩.১২"));
                add(new QuestionModule("সমান্তর ধারায় ৫ম পদের মান ১২ এবং সাধারণ পার্থক্য ২। প্রথম পদের মান কত?", "C", "১", "৬", "৪", "২"));
                add(new QuestionModule("যদি x + y = 5 এবং x - y = 3, তবে x এর মান কত?", "A", "৪", "৬", "৮", "২"));
                add(new QuestionModule("সমকোণী ত্রিভুজে, অতিভুজ কোনটি?", "B", "সবচেয়ে ছোট বাহু", "সবচেয়ে বড় বাহু", "সমান বাহু", "উচ্চতা"));
                add(new QuestionModule("দুটি কোণের সমষ্টি ৯০° হলে তাদের কী বলা হয়?", "D", "সম্পূরক কোণ", "সমকোণ", "প্রতিসম কোণ", "সজাতি কোণ"));

            }
        };
        QuestionModule.createQuestionsForSubject("গণিত" , R.drawable.tools, questions);











    }




//====================================================================
// ====================================================================
//====================================================================
// ====================================================================

}
