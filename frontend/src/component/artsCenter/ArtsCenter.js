import MyButton from "../button/MyButton";
import Profile from "../home/homeContents/Profile";
import Friends from "../home/homeContents/Friends";
import { useNavigate } from "react-router-dom";
import LogoHeader from "../LogoHeader";
import ArtsCenterInProgress from "./ArtsCenterInProgress";

const ArtsCenter = () =>{
    const navigate = useNavigate();
    
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
                    <ArtsCenterInProgress/>
                </div>
            </div>
            <div className="sub_wrapper">
                <Profile/>
                <Friends/>
            </div>
        </div>
    );
}
export default ArtsCenter;