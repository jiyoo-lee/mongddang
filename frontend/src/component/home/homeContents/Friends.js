import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import GetAxios from "../../../utils/axios/GetAxios";

const Friends = () => {
    const navigate = useNavigate();

    const [friendRecommend, setFriendRecommend] = useState([]);

    useEffect(()=>{
        GetAxios('following/recommend',{params:{}},(res)=>{setFriendRecommend(res.data)})
    },[])

    return (
        <div className="friends_wrapper">
        <div className="recommend">
        <br/>
        <div className="friends_message">알 수도 있는 친구들</div>
        <br/>
        <br/>
        {
            friendRecommend && 
            friendRecommend.length < 1 ? <div>
                <img className="friendsRecommend" src={process.env.PUBLIC_URL + `../.././img/present_logo.png`}/>
            <br/>
                <span className="message">추천 친구가 없습니다. 친구를 팔로우 해보세요 !</span>
            </div> :
            (friendRecommend.map(friend=> (
                <div key={friend.memberId} className="friends_recommend_wrapper">
                    <img className="friendsRecommend" src={friend.profileUrl} alt="profile" onClick={()=>(navigate("/drawer/"+friend.memberId))}/>
                <br/>
                    <span>{friend.nickname}</span>
                </div>
            )))
        }    
        <br/>
        </div>
        </div>
    );
}

export default Friends;