import { useEffect } from "react";
import GetAxios from "../../../utils/axios/GetAxios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const UploadedFriends = () => {

    const navigate = useNavigate();

    const [lastUpdates, setLastUpdates] =useState([]);
    let userId = sessionStorage.getItem("userId");

    useEffect(()=>{
        GetAxios('/following/last-updated/'+userId, {params:{}},(res)=>{setLastUpdates(res.data)
        })
    },[])

    return (
    <div className="contents_wrapper">
        최근 일주일 내 업로드한 친구들
    <br/>
    <br/>
        {lastUpdates.length !== 0 ? lastUpdates.map(last => (
        <span key={last.memberId}>
            <img className="friendsRecommend" 
                    src={last.profileUrl === null ? process.env.PUBLIC_URL + `../.././img/present_logo.png`: last.profileUrl} 
                    alt={last.memberId} onClick={()=>navigate('/drawer/'+last.memberId)}/>
        </span>
        )): <span style={{"fontWeight":"lighter"}}>최근 업데이트한 친구가 없습니다</span>} 
    </div>
    );
}

export default UploadedFriends;