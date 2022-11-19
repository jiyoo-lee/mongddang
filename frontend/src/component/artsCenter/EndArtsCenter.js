import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import GetAxios from "../../utils/axios/GetAxios";
import LogoHeader from "../LogoHeader";
import MyButton from "../button/MyButton";
import Profile from "../home/homeContents/Profile";
import Friends from "../home/homeContents/Friends";
import LastArtsCenterWinners from "./LastArsCenterWinners";

const EndArtsCenter = () => {
    const navigate = useNavigate();
    const [contests, setContests] = useState([]);

    const [winners, setWinners] =useState([]);
    const [showWinners, setShowWinners] = useState(false);

    const getWinnersByContest = (contestId) =>{
        
        if(!showWinners){
            GetAxios('/arts-center/'+contestId,{params:{}},(res)=>{
            setWinners(res.data)
            setShowWinners(true)
            })
        }else{
            setShowWinners(false);
        }
    }

    useEffect(()=>{
        GetAxios('/arts-center/end', {params:{}}, (res)=>(setContests(res.data)));
    }, []);

    return(
        <>
        <div className="all_wrapper">
            <LogoHeader/>
            <div className="main_wrapper">
                <div className="menu_wrapper">
                    <MyButton text={'진행 중'} onClick={()=>{navigate('/arts-center')}}/>
                    <MyButton text={'지난 공모전'} type={'nothing'} onClick={()=>{navigate('/arts-center/end')}}/>
                </div>
                <div className="home_wrapper">
                    <br/>
                    <br/>
                    <div className="arts_center_wrapper">
                        <img className='arts_center_logo' src={process.env.PUBLIC_URL + `.././img/arts_center_logo.png`} />
                        <br/>
                        <br/>
                    </div>
                    <div className="status_box">지난 공모전, 최고 몽땅을 받은 사람은 누구일까요?</div>
                        <br/>
                        <div className="contest_wrapper">
                            {contests && contests.map(contest=> (
                                <div className="contest_box" key={contest.contestId}>
                                    {/* <img className="contest_poster" src={contest.posterUrl} 
                                            alt="poster" onClick={()=>navigate('/arts-center/' + contest.contestId)} 
                                            onClick={()=>navigate('/arts-center/' + contest.contestId, {state:{contest: contest}})}/> */}
                                    <img className="contest_poster" src={process.env.PUBLIC_URL + `.././img/november_contest_img.png`} 
                                        alt="poster" onClick={()=>{getWinnersByContest(contest.contestId)}}/>
                                    <br/>
                                    <div className="contest_title">{contest.title}
                                        <p>{contest.startDay} ~ {contest.endDay}</p>
                                    </div>
                                </div>
                            ))}
                        </div>
                    {showWinners?  <LastArtsCenterWinners winners={winners}/> : <></> }
                    </div>
                </div>
            <div className="sub_wrapper">
                <Profile/>
                <Friends/>
            </div>
        </div>
        </>
    );
}

export default EndArtsCenter;