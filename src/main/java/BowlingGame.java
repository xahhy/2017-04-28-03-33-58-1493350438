public class BowlingGame {

public class Frame{
        public int num = 0;//how many balls in this frame
        public String frame = "";
        public int cur_score = 0;//score in this frame
        public int score_1 = 0;//the first ball's score
    }
    
    public int getBowlingScore(String bowlingCode) {
        int score_all = 0;
        final int FRAME_NUMS = 10;
        String[] frame_str = bowlingCode.split("\\|");
        Frame[] m_frame = new Frame[FRAME_NUMS+1];
        int frame_num=0;
        for(int i = 0 ; i < frame_str.length ; i++)
        {
            if(frame_str[i].length()==0)
            {
                continue;
            }
            //1: create a Frame object
            m_frame[frame_num] = new Frame();
            m_frame[frame_num].num = frame_str[i].length();
            m_frame[frame_num].frame = frame_str[i];

            //2: calculate current frame score
            int cur_score = 0;
            if(frame_str[i].indexOf('X') != -1 )
            {
                cur_score = frame_str[i].length() == 1 ? 10 : 20;
            }
            else if(frame_str[i].indexOf('/') != -1)
            {
                cur_score = 10;
            }
            else
            {
                for(int j = 0 ; j < frame_str[i].length() ; j++)
                {
                    
                    if(Character.isDigit(frame_str[i].charAt(j))){                      
                        cur_score += Character.getNumericValue(frame_str[i].charAt(j));
                    }                   
                }       
            }           
            m_frame[frame_num].cur_score = cur_score;
            
            //3: calculate first character's score:
            //'<number>'	: number
            //'X'			: 10
            // else			: 0
            if(Character.isDigit(frame_str[i].charAt(0))){
                m_frame[frame_num].score_1 = Character.getNumericValue(frame_str[i].charAt(0));
            }
            else if(frame_str[i].charAt(0) == 'X'){
                m_frame[frame_num].score_1 = 10;
            }
            
            //4: next frame
            frame_num++;
        }
        
        //calculate the total score 
        for(int i = 0 ;i < FRAME_NUMS ; i++){
            int score_1 = 0;
            int score_2 = 0;
            //calculate extra score:
            //for each frame , divided into 3 situations:
            //1: X
            //2: /
            //3: number
            
            //1: X
            if(m_frame[i].frame.indexOf('X') != -1)
            {
                if(m_frame[i+1].num == 1)
                {
                    //next frame has 1 ball.
                    score_1 = m_frame[i+1].cur_score;
                    //find next frame's next frame
                    score_2 = m_frame[i+2].score_1;
                }
                else
                {
                    //next frame has 2 balls.
                    score_1 = m_frame[i+1].cur_score;
                }
            }
            else if(m_frame[i].frame.indexOf('/') != -1)
            {
                //2: /
            	score_1 = m_frame[i+1].score_1;
            }
            //3: number --> no extra score
                     
            score_all += m_frame[i].cur_score;
            score_all += score_1;
            score_all += score_2;           
        }
        
        return score_all;
    }
}
