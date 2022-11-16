import MyButton from "../button/MyButton";
import Profile from "../home/homeContents/Profile";
import Friends from "../home/homeContents/Friends";
import { useNavigate, useParams } from "react-router-dom";
import MyGuestBook from "./MyGuestBook";
import OtherGuestBook from "./OtherGuestBook";


const Guestbook = () => {
    const navigate = useNavigate();
    
    const userIdOnSession = sessionStorage.getItem("userId");
    const { userId } = useParams();

    return (
    <div className="all_wrapper">
        <img className='login_logo' src={process.env.PUBLIC_URL + `../.././img/login_logo.png`} onClick={()=> {navigate('/home/feed')}} alt='login_logo'/>
        <div className="main_wrapper">
            <div className="menu_wrapper">
                <MyButton text={'방명록'} type={'nothing'} onClick={()=>{navigate('/home/feed')}}/>
            </div>
            <div className="home_wrapper">
                {userId === userIdOnSession ? <MyGuestBook/> : <OtherGuestBook/>}
            </div>
        </div>
        <div className="sub_wrapper">
            <Profile/>
            <Friends/>
        </div>
    </div>
    );

}
export default Guestbook;