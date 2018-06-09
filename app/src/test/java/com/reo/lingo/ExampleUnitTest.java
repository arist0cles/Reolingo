//package com.reo.lingo;
//
//import com.reo.lingo.Activities.MainActivity;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.RobolectricTestRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import jsonobj.Module;
//import jsonobj.Question;
//
//import static org.junit.Assert.*;
//
///**
// * Example local unit test, which will execute on the development machine (host).
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//@RunWith(RobolectricTestRunner.class)
//public class ExampleUnitTest {
//
//    private MainActivity underTest;
//    private List<Question> mockList;
//    private Module testModule;
//
//    public void setup(){
//        underTest = new MainActivity();
//        mockList = new ArrayList<>();
//        testModule = new Module();
//        testModule.level = 1;
//    }
//
//    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }
//
//    @Test
//    public void getQuestionReturnsAFourTileQuestion(){
//        setup();
//
//        Question q = new Question();
//        q.type = "FOUR_TILE_QUESTION";
//        mockList.add(q);
//        testModule.questions = mockList;
//
//        assertEquals(underTest.getQuestions(testModule).size(), 1);
//    }
//}