import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import GetAxios from "../../utils/axios/GetAxios";

const ArtsCenterInProgress = () =>{
    const navigate = useNavigate();
    const [ artsCenter, setArtsCenter ] = useState([]);

    useEffect(()=>{
        GetAxios('/arts-center/',{params:{}},(res)=>{setArtsCenter(res.data)})
    }, []);

    return(
        <>
        <div className="arts_center_wrapper">
            <img className='arts_center_logo' src={process.env.PUBLIC_URL + `.././img/arts_center_logo.png`} />
        <br/>
        <br/>
        </div>
        <div className="status_box">현재 진행 중인 공모전, 지금 참여해보세요!</div>
        <br/>
        <div className="contest_wrapper">
        {artsCenter && artsCenter.map(contest=> (
            <div className="contest_box" key={contest.contestId}>
                {/* <img className="contest_poster" src={contest.posterUrl} 
                         alt="poster" onClick={()=>navigate('/arts-center/' + contest.contestId)} 
                         onClick={()=>navigate('/arts-center/' + contest.contestId, {state:{contest: contest}})}/> */}
                <img className="contest_poster" src={process.env.PUBLIC_URL + `.././img/november_contest_img.png`} 
                     alt="poster" onClick={()=>navigate('/arts-center/' + contest.contestId, {state:{contest: contest}})}/>
                <br/>
                <div className="contest_title">{contest.title}
                    <p>{contest.startDay} ~ {contest.endDay}</p>
                </div>
            </div>
        ))}
        </div>
        </>
    );
}

export default ArtsCenterInProgress;