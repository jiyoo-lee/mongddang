import MyButton from "./button/MyButton";
import LogoHeader from "./LogoHeader";
import Profile from "./home/homeContents/Profile";
import Friends from "./home/homeContents/Friends";
import { useNavigate, useParams } from "react-router-dom";
import MyContestComments from "./MyContestComments";


const ContestComment = () => {

    const navigate = useNavigate();
    const {paintingId} = useParams();

    return (
        <div className="all_wrapper">
        <LogoHeader/>
    <div className="main_wrapper">
        <div className="menu_wrapper">
            <MyButton text={'진행 중'} type={'nothing'} onClick={()=>{navigate('/arts-center')}}/>
            <MyButton text={'지난 공모전'} onClick={()=>{navigate('/arts-center/last')}}/>
        </div>
    <div className="home_wrapper">
    <br/>
    <br/>
        <MyContestComments paintingId={paintingId}/>
    </div>
    </div>
    <div className="sub_wrapper">
        <Profile/>
        <Friends/>
    </div>
    </div>

    );
}

export default ContestComment;