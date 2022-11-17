import { useNavigate } from "react-router-dom";
import MyButton from "./button/MyButton";

const LogoHeader = () => {
    const navigate = useNavigate();

    const handleLogout = ()=> {
        sessionStorage.clear();
        navigate('/');
    }

    return (
        <>
            <img className='login_logo' src={process.env.PUBLIC_URL + `.././img/login_logo.png`} 
                 onClick={()=> {navigate('/home/feed')}} alt='login_logo'/>
            <MyButton text={'logout'} onClick={handleLogout}/>
        </>
    );
}

export default LogoHeader;