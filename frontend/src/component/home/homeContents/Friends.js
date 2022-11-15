import { useNavigate } from "react-router-dom";

const Friends = () => {
    const navigate = useNavigate();

    return (
        <div className="friends_wrapper">
        <div className="recommend">
        <br/>
        <div className="friends_message">알 수도 있는 친구들</div>
        <br/>
        <br/>
            <img className="friendsRecommend" src='../.././img/test.jpg' alt="profile" onClick={()=>(navigate("/drawer/jeeyu"))}/>
            <img className="friendsRecommend" src='../.././img/test2.jpg' alt="profile"/>
            <img className="friendsRecommend" src='../.././img/test3.png' alt="profile"/>
            <img className="friendsRecommend" src='../.././img/test4.jpeg' alt="profile"/>
        <br/>
        </div>
        </div>
    );
}

export default Friends;